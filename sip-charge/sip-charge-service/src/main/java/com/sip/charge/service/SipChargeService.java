package com.sip.charge.service;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author bystander
 * @date 2018/9/13
 */
@SpringBootApplication
@MapperScan(value = "com.sip.charge.service.mapper")
@Slf4j
public class SipChargeService {

    public static void main(String[] args) {

        SpringApplication.run(SipChargeService.class);
        log.info("start SipChargeService");
    }
}
