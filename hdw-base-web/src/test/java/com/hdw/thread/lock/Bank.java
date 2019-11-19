package com.hdw.thread.lock;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Descripton com.hdw.thread.lock
 * @Author TuMinglong
 * @Date 2019/6/6 10:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Bank {
    private Integer id;

    private String name;

    private int count;

}
