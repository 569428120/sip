package com.sip.charge.service.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sip.charge.model.SysRouteModel;
import com.sip.charge.service.service.ISysRouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system-route")
public class SysRouteController {
    @Resource
    private ISysRouteService sysRouteService;

    /**
     * 获取预制数据
     *
     * @return ResponseEntity<List < SysBoardingTypeModel>>
     */
    @GetMapping("/gets/all")
    public ResponseEntity<List<SysRouteModel>> getSysBoardings() {
        return ResponseEntity.ok(sysRouteService.selectList(new QueryWrapper()));
    }
}
