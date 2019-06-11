package com.sip.charge.service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sip.charge.model.UserModel;
import com.sip.charge.service.SipChargeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SipChargeService.class)
public class IUserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void select() {
        userService.selectList(new QueryWrapper<UserModel>()
                .eq("name", "aa"));
    }

    public void insert(){
        userService.insertBatch(null);
    }

}