package com.tensquare.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author zhang
 * @Date 2020/5/10 11:09
 * @Version 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class);
    }
}
