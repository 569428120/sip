package com.sip.charge.service.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sip.charge.model.ChargeProjectModel;
import com.sip.charge.model.SysBoardingTypeModel;
import com.sip.charge.service.service.ISysBoardingTypeService;
import com.sip.common.vo.PageResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 寄宿方式系统预制数据
 */
@RestController
@RequestMapping("/system-boarding")
public class SysBoardingTypeController {
    @Resource
    private ISysBoardingTypeService sysBoardingTypeService;

    /**
     * 获取预制数据
     *
     * @return ResponseEntity<List < SysBoardingTypeModel>>
     */
    @GetMapping("/gets/all")
    public ResponseEntity<List<SysBoardingTypeModel>> getSysBoardings() {
        return ResponseEntity.ok(sysBoardingTypeService.selectList(new QueryWrapper()));
    }
}
