package com.sip.charge.service.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sip.charge.model.SysRouteModel;
import com.sip.charge.service.service.ISysBoardingTypeService;
import com.sip.charge.service.service.ISysClassService;
import com.sip.charge.service.service.ISysRouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system-common")
public class SysCommonController {

    @Resource
    private ISysClassService sysClassService;

    @Resource
    private ISysRouteService sysRouteService;

    @Resource
    private ISysBoardingTypeService sysBoardingTypeService;

    /**
     * 交通路线，班级，寄读方式 三合一接口
     *
     * @return ResponseEntity<List < SysBoardingTypeModel>>
     */
    @GetMapping("/gets/all")
    public ResponseEntity<Map<String, Object>> getSysBoardings() {
        Map<String, Object> map = new HashMap<>(3);
        map.put("classData", sysClassService.selectList(new QueryWrapper<>()));
        map.put("routeData", sysRouteService.selectList(new QueryWrapper<>()));
        map.put("boardingData", sysBoardingTypeService.selectList(new QueryWrapper<>()));
        return ResponseEntity.ok(map);
    }
}
