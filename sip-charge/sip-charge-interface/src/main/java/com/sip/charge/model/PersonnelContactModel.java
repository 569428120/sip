package com.sip.charge.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sip.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 联系方式
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_sip_charge_personnel_contact")
public class PersonnelContactModel extends BaseModel {
    private Long personnelId;
    /**
     * 亲属关系
     */
    private String relation;
    /**
     * 联系人的名称
     */
    private String relationName;
    /**
     * 通讯类型
     */
    private String contactType;
    /**
     * 号码
     */
    private String number;
    /**
     * 描述
     */
    private String description;
}
