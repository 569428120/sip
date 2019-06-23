package com.sip.charge.service.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sip.charge.model.SysClassModel;
import com.sip.charge.model.SysRouteModel;
import com.sip.charge.service.service.ISysClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system-class")
public class SysClassController {

    @Resource
    private ISysClassService sysClassService;

    /**
     * 获取预制数据
     *
     * @return ResponseEntity<List < SysClassModel>>
     */
    @GetMapping("/gets/all")
    public ResponseEntity<List<SysClassModel>> getSysBoardings() {
        return ResponseEntity.ok(sysClassService.selectList(new QueryWrapper<>()));
    }
}
