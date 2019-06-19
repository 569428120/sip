package com.sip.charge.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sip.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 人员和收费项中间表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_sip_charge_personnel_to_details")
public class PersonnelToDetailsModel extends BaseModel {
    private Long personnelId;
    private Long detailsId;
    /**
     * 状态  取消 等
     */
    private String chargeStatus;
}
