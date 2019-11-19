package com.hdw.common.constants;

/**
 * @Description 常量枚举
 * @Author TuMingLong
 * @Date 2019/11/1 11:22
 */
public enum CommonEnum {
    /**
     * 目录
     */
    CATALOG(0, "目录"),
    /**
     * 菜单
     */
    MENU(1, "菜单"),
    /**
     * 按钮
     */
    BUTTON(2, "按钮"),

    /** 定时任务状态 */
    /**
     * 正常
     */
    NORMAL(0, "正常"),
    /**
     * 暂停
     */
    PAUSE(1, "暂停"),

    /** 文件存储*/
    /**
     * 本地
     */
    LOCAL(0, "本地"),
    /**
     * fastdfs
     */
    FASTDFS(1, "fastdfs"),
    /**
     * 七牛云
     */
    QINIU(2, "七牛云"),
    /**
     * 阿里云
     */
    ALIYUN(3, "阿里云"),
    /**
     * 腾讯云
     */
    QCLOUD(4, "腾讯云"),

    /**
     * 菜单，按钮状态
     */
    YES(0, "是"),

    NO(1, "不是"),

    OPEN(0, "开启"),

    CLOSE(1, "关闭"),


    ;

    private final int code;
    private final String msg;

    CommonEnum(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
