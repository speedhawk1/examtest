package com.example.test;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Administrator
 * 2016/12/17
 */
public class Test {
    public static void main(String[] args) {
        String password = "a";
        System.out.println(DigestUtils.md5Hex(password));
    }
}
