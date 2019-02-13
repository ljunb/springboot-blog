package com.example.iblog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    /**
     * 作者id
     */
    @Null
    private BigInteger authorId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private int sex;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 住址
     */
    private String address;
}
