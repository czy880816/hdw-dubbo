package com.hdw.websocket;


import com.hdw.common.utils.JacksonUtils;
import com.hdw.sms.entity.Sms;
import com.hdw.sms.entity.SmsRecord;
import com.hdw.sms.service.ISmsRecordService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author TuMinglong
 * @Description 页面推送消息
 * @date 2018/6/28 21:19
 */

@Log4j2
@ServerEndpoint(value = "/ws/sms/{userId}")
@Component
public class SmsPushSocket {

    //这里使用静态，让 service 属于类
    private static ISmsRecordService smsRecordService;

    // 注入的时候，给类的 service 注入
    @Reference
    public void setChatService(ISmsRecordService smsRecordService) {
        SmsPushSocket.smsRecordService = smsRecordService;
    }

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
    private static CopyOnWriteArraySet<SmsPushSocket> webSocketSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //用户Id
    private String userId;

    // 控制线程数，最优选择是处理器线程数*3，本机处理器是6线程
    private final static int THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 3;

    /**
     * 连接建立成功
     *
     * @param session 当前会话session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        try {
            this.session = session;
            webSocketSet.add(this); //加入set中
            addOnlineCount(); //在线数加1
            this.userId = userId;
            log.info("SmsPushSocket: 有新窗口开始监听：" + userId + ",当前在线人数为：" + getOnlineCount());
            //连接成功，发送消息
        } catch (Exception e) {
            e.printStackTrace();
            log.info("SmsPushSocket: websocket IO异常");
        }
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        //从set中删除
        boolean b = webSocketSet.remove(this);
        if (b && getOnlineCount() > 0) {
            subOnlineCount(); //在线人数减1
            log.info("SmsPushSocket: 有一连接关闭，当前在线人数为：" + getOnlineCount());
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 当前会话session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("SmsPushSocket: 收到来自窗口" + userId + "的信息:" + message);
        try {
            SmsPushSocket _this = getCurrentWebSocket(userId);
            if (_this == null) {
                log.info("SmsPushSocket: 用户：" + userId + "不在线");
                return;
            }
            _this.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SmsPushSocket: webSocket发送消息异常：登录用户：" + userId);
        }
    }

    /**
     * 发生错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
        log.error("SmsPushSocket: 发生错误");
    }

    /**
     * 实现服务器主动推送
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
            log.info("SmsPushSocket: 成功发送一条消息:" + message);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("SmsPushSocket: 发送消息异常");
        }
    }

    /**
     * 群发自定义消息
     *
     * @param userId
     * @param message
     */
    public void sendInfo(String userId, String message) {
        try {
            if (userId == null || StringUtils.isBlank(message)) {
                return;
            }
            Sms sms = JacksonUtils.toObject(message, Sms.class);

            List<SmsPushSocket> _thisList = getCurrentWebSocketList(userId);
            if (_thisList == null) {
                SmsRecord smsRecord = new SmsRecord();
                smsRecord.setId(Long.valueOf(sms.getId()));
                smsRecord.setPushTime(new Date());
                smsRecord.setStatus(2);
                smsRecordService.updateById(smsRecord);
                log.info("SmsPushSocket: 用户：" + userId + "不在线");
                return;
            }
            ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
            final CountDownLatch latch = new CountDownLatch(_thisList.size());
            _thisList.forEach(_this -> {
                if (!pool.isShutdown()) {
                    pool.execute(() -> {
                        try {
                            _this.sendMessage(message);
                        } finally {
                            latch.countDown();
                        }
                    });
                }
            });
            //等待所有线程执行完毕
            latch.await();
            //关闭线程池
            pool.shutdown();

            SmsRecord smsRecord = new SmsRecord();
            smsRecord.setId(Long.valueOf(sms.getId()));
            smsRecord.setPushTime(new Date());
            smsRecord.setStatus(1);
            smsRecordService.updateById(smsRecord);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SmsPushSocket：webSocket发送消息异常：登录用户：" + userId);
        }
    }

    public static synchronized int getOnlineCount() {
        return SmsPushSocket.onlineCount;
    }

    public static synchronized void addOnlineCount() {
        SmsPushSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        SmsPushSocket.onlineCount--;
    }


    /**
     * 根据当前登录用户ID获取webSocket对象
     *
     * @param userId
     * @return
     */
    public static SmsPushSocket getCurrentWebSocket(String userId) {
        if (userId == null || webSocketSet == null || webSocketSet.size() < 1) {
            return null;
        }
        for (SmsPushSocket item : webSocketSet) {
            if (item.userId.equals(userId)) {
                return item;
            }
        }
        return null;
    }

    /**
     * 根据当前登录用户ID获取webSocket对象集合
     *
     * @param userId
     * @return
     */
    public static List<SmsPushSocket> getCurrentWebSocketList(String userId) {
        List<SmsPushSocket> list = new ArrayList<>();
        if (userId == null || webSocketSet == null || webSocketSet.size() < 1) {
            return null;
        }
        for (SmsPushSocket item : webSocketSet) {
            String tempId = item.userId.substring(0, item.userId.lastIndexOf("_"));
            //log.info("来自客户端ID：" + tempId + " 登录的ID：" + userId);
            if (tempId.equals(userId)) {
                list.add(item);
            }
        }
        return list;
    }
}
