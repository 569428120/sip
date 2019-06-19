package com.sip.charge.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sip.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 人员详情
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_sip_charge_personnel")
public class ChargePersonnelModel extends BaseModel {
    /**
     * 学号
     */
    private String studentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 班级编码
     */
    private String classCode;

    /**
     * 寄读方式
     */
    private String boardingCode;


    /**
     * 交通路线
     */
    private String routesCode;

    /**
     * 状态
     */
    private String chargeStatus;

    /**
     * 通知状态d
     */
    private String noticeStatus;

}
