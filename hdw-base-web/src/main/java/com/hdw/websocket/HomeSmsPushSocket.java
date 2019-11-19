package com.hdw.websocket;


import com.hdw.sms.entity.Sms;
import com.hdw.sms.entity.SmsRecord;
import com.hdw.sms.entity.UnreadSms;
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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author TuMinglong
 * @Description 首页推送消息
 * @date 2018/6/28 21:19
 */

@Log4j2
@ServerEndpoint(value = "/ws/homeSms/{userId}")
@Component
public class HomeSmsPushSocket {

    //这里使用静态，让 service 属于类
    private static ISmsRecordService smsRecordService;

    // 注入的时候，给类的 service 注入
    @Reference
    public void setChatService(ISmsRecordService smsRecordService) {
        HomeSmsPushSocket.smsRecordService = smsRecordService;
    }

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
    private static CopyOnWriteArraySet<HomeSmsPushSocket> webSocketSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //用户Id
    private String userId;

    // 控制线程数，最优选择是处理器线程数*3，本机处理器是6线程
    private final static int THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 3;

    /**
     * 连接建立成功调用的方法
     *
     * @param session 当前会话session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        try {
            this.session = session;
            this.userId = userId;
            webSocketSet.add(this); //加入set中
            addOnlineCount(); //在线数加1
            String tempId = this.userId.substring(0, this.userId.lastIndexOf("_"));
            log.info("SmsPushSocket: 有新窗口开始监听：" + tempId + ",当前在线人数为：" + getOnlineCount());

            //连接成功，发送消息
            Integer count = smsRecordService.findUnreadMessagesCount(Long.valueOf(tempId));
            List<SmsRecord> smsRecordList = smsRecordService.findRecent5Messages(Long.valueOf(tempId));
            UnreadSms unreadSms = new UnreadSms();
            unreadSms.setCount(count);
            List<Sms> smsList = new ArrayList<>();
            if (!smsRecordList.isEmpty()) {
                smsRecordList.forEach(smsRecord -> {
                    Sms sms = new Sms();
                    sms.setId(smsRecord.getId().toString());
                    sms.setTitle(smsRecord.getTitle());
                    sms.setContent(smsRecord.getContent());
                    sms.setTimeStr(smsRecord.getTimeStr());
                    sms.setStatus(smsRecord.getStatus());
                    smsList.add(sms);
                });
            }
            unreadSms.setList(smsList);
            this.sendMessage(unreadSms.toString());
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
        String tempId = this.userId.substring(0, this.userId.lastIndexOf("_"));
        try {
            HomeSmsPushSocket _this = getCurrentWebSocket(userId);
            if (_this == null) {
                log.info("SmsPushSocket: 用户：" + tempId + "不在线");
                return;
            }
            List<String> userIds = new ArrayList<>();
            if (message.contains(",")) {
                String[] targetList = message.split(",");
                userIds.addAll(Arrays.asList(targetList));
            } else {
                userIds.add(message);
            }
            String[] ids = new String[userIds.size()];
            userIds.toArray(ids);
            smsRecordService.updateMessageStatus(ids);
            Integer count = smsRecordService.findUnreadMessagesCount(Long.valueOf(tempId));
            List<SmsRecord> smsRecordList = smsRecordService.findRecent5Messages(Long.valueOf(tempId));
            UnreadSms unreadSms = new UnreadSms();
            unreadSms.setCount(count);
            List<Sms> smsList = new ArrayList<>();
            if (!smsRecordList.isEmpty()) {
                smsRecordList.forEach(smsRecord -> {
                    Sms sms = new Sms();
                    sms.setId(smsRecord.getId().toString());
                    sms.setTitle(smsRecord.getTitle());
                    sms.setContent(smsRecord.getContent());
                    sms.setTimeStr(smsRecord.getTimeStr());
                    sms.setStatus(smsRecord.getStatus());
                    smsList.add(sms);
                });
            }
            unreadSms.setList(smsList);
            this.sendMessage(unreadSms.toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SmsPushSocket: webSocket发送消息异常：登录用户：" + tempId);
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
            List<HomeSmsPushSocket> _thisList = getCurrentWebSocketList(userId);
            if (_thisList == null) {
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

        } catch (Exception e) {
            e.printStackTrace();
            log.error("SmsPushSocket：webSocket发送消息异常：登录用户：" + userId);
        }
    }

    public static synchronized int getOnlineCount() {
        return HomeSmsPushSocket.onlineCount;
    }

    public static synchronized void addOnlineCount() {
        HomeSmsPushSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        HomeSmsPushSocket.onlineCount--;
    }


    /**
     * 根据当前登录用户ID获取webSocket对象
     *
     * @param userId
     * @return
     */
    public static HomeSmsPushSocket getCurrentWebSocket(String userId) {
        if (userId == null || webSocketSet == null || webSocketSet.size() < 1) {
            return null;
        }
        for (HomeSmsPushSocket item : webSocketSet) {
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
    public static List<HomeSmsPushSocket> getCurrentWebSocketList(String userId) {
        List<HomeSmsPushSocket> list = new ArrayList<>();
        if (userId == null || webSocketSet == null || webSocketSet.size() < 1) {
            return null;
        }
        for (HomeSmsPushSocket item : webSocketSet) {
            String tempId = item.userId.substring(0, item.userId.lastIndexOf("_"));
            //log.info("来自客户端ID：" + tempId + " 登录的ID：" + userId);
            if (tempId.equals(userId)) {
                list.add(item);
            }
        }
        return list;
    }
}
