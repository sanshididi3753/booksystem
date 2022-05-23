package com.nmsl.feign.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Mail implements Serializable {
    private String email;
    private String confirmCode;
}
