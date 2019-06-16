package com.sip.charge.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.sip.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 交通路线系统预制表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_sip_sys_routes")
public class SysRouteModel extends BaseModel {
    private String name;
    private String code;
    private String description;
}
