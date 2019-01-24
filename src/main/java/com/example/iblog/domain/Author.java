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
    @Null private BigInteger authorId;
    private String name;
    private int sex;
    private Date birthday;
    private String address;
}
