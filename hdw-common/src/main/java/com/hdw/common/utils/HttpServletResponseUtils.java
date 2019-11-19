package com.hdw.common.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author TuMingLong
 * @Date 2019/11/1 12:03
 */
public final class HttpServletResponseUtils {

    private static String UTF8 = "UTF-8";
    private static String CONTENT_TYPE = "application/json";

    private HttpServletResponseUtils() {
        throw new AssertionError();
    }

    public static void printJSON(HttpServletResponse response, Object object) throws Exception {
        response.setCharacterEncoding(UTF8);
        response.setContentType(CONTENT_TYPE);
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(object));
        printWriter.flush();
        printWriter.close();
    }
}
