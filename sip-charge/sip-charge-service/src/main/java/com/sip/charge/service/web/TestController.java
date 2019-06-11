package com.sip.charge.service.web;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sip.charge.model.UserModel;
import com.sip.charge.service.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private IUserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserModel>> queryUser() {

        return ResponseEntity.ok(userService.selectList(new QueryWrapper<UserModel>()));
    }
}
