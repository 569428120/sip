package com.sip.charge.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sip.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 寄读方式
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_sip_sys_boarding_type")
public class SysBoardingTypeModel extends BaseModel {
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 描述
     */
    private String description;
}
