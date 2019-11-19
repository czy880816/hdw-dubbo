package com.hdw.system.interceptor;

import com.hdw.common.exception.GlobalException;
import com.hdw.system.properties.HdwCommonProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description 上传文件类型拦截器
 * @Author TuMingLong
 * @Date 2019/11/1 13:54
 */
@Slf4j
public class UploadInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private HdwCommonProperties commonProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean flag = true;
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> files = multipartRequest.getFileMap();
            Iterator<String> iterator = files.keySet().iterator();
            // 对多部件请求资源进行遍历
            while (iterator.hasNext()) {
                String formKey = (String) iterator.next();
                MultipartFile multipartFile = multipartRequest.getFile(formKey);
                String filename = multipartFile.getOriginalFilename();
                // 判断是否为限制文件类型
                if (!checkFile(filename)) {
                    new GlobalException("不支持上传此格式文件");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * 判断是否为允许的上传文件类型,true表示允许
     */
    private boolean checkFile(String fileName) {
        List<String> suffixList = commonProperties.getAllowUploadFileExtensions();
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (suffixList.contains(suffix)) {
            return true;
        }
        return false;
    }
}
