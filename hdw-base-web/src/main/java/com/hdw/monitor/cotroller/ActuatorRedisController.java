package com.hdw.monitor.cotroller;

import com.hdw.common.result.CommonResult;
import com.hdw.monitor.entity.RedisInfo;
import com.hdw.monitor.service.IRedisInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description Redis信息
 * @Author TuMingLong
 * @Date 2019/11/13 15:51
 */
@Slf4j
@RestController
@RequestMapping("/actuator/redis")
public class ActuatorRedisController {

    @Autowired
    private IRedisInfoService redisInfoService;

    /**
     * Redis详细信息
     * @return
     * @throws Exception
     */
    @GetMapping("/info")
    public CommonResult<List<RedisInfo>> getRedisInfo() throws Exception {
        List<RedisInfo> infoList = this.redisInfoService.getRedisInfo();
        log.info(infoList.toString());
        return CommonResult.ok().data(infoList);
    }

    @GetMapping("/keysSize")
    public Map<String, Object> getKeysSize() throws Exception {
        return redisInfoService.getKeysSize();
    }

    @GetMapping("/memoryInfo")
    public Map<String, Object> getMemoryInfo() throws Exception {
        return redisInfoService.getMemoryInfo();
    }

    /**
     * @功能：获取磁盘信息
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/queryDiskInfo")
    public CommonResult<List<Map<String,Object>>> queryDiskInfo(HttpServletRequest request, HttpServletResponse response){
        try {
            // 当前文件系统类
            FileSystemView fsv = FileSystemView.getFileSystemView();
            // 列出所有windows 磁盘
            File[] fs = File.listRoots();
            log.info("查询磁盘信息:"+fs.length+"个");
            List<Map<String,Object>> list = new ArrayList<>();

            for (int i = 0; i < fs.length; i++) {
                if(fs[i].getTotalSpace()==0) {
                    continue;
                }
                Map<String,Object> map = new HashMap<>();
                map.put("name", fsv.getSystemDisplayName(fs[i]));
                map.put("max", fs[i].getTotalSpace());
                map.put("rest", fs[i].getFreeSpace());
                map.put("restPPT", (fs[i].getTotalSpace()-fs[i].getFreeSpace())*100/fs[i].getTotalSpace());
                list.add(map);
                log.info(map.toString());
            }
            return CommonResult.ok().data(list);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.fail().msg("运行异常，请联系管理员");
        }
    }
}
