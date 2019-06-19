package com.sip.charge.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sip.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 减免
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_sip_charge_personnel_reduction")
public class PersonnelReductionModel extends BaseModel {

    private Long personnelId;

    /**
     * 减免类型 如书本费
     */
    private String reductionType;
    /**
     * 操作人id
     */
    private Long operationId;

    private String operationName;
    /**
     * 审批人
     */
    private Long approvalId;

    private String approvalName;

    /**
     * 审批时间
     */
    private Date approvalTime;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 描述
     */
    private String description;
}
