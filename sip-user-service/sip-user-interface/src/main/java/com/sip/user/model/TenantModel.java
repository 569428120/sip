package com.sip.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sip.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 租户信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_sip_charge_details")
public class TenantModel extends BaseModel {

    private String province;

    private String city;

    private String company;

    private String tenantName;

    private Long tenantUserId;

    private Date termOfValidityTime;

    private String tenantStatus;

}
