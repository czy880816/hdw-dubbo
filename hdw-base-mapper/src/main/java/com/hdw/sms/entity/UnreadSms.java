package com.hdw.sms.entity;

import com.hdw.common.utils.JacksonUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 未读消息
 * @Author TuMinglong
 * @Date 2019/7/18 10:36
 */
@Data
@EqualsAndHashCode
public class UnreadSms implements Serializable {

    /**
     * 未读消息数量
     */
    private Integer count;

    private List<Sms> list;

    @Override
    public String toString() {
        return JacksonUtils.toJson(this);
    }
}
