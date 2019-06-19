package com.sip.charge.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sip.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_sip_charge_details")
public class ChargeDetailsModel extends BaseModel {

    public static final String CHARGE_TYPE_ITEM = "charge_item";
    public static final String CHARGE_TYPE_TYPE = "charge_type";

    public static final Integer TRAFFIC_TRUE = 1;
    public static final Integer TRAFFIC_FALSE = 1;

    /**
     * 所属收费项目的id
     */
    private Long projectId;

    /**
     * 收费类型  还是收费项  charge_type charge_item
     */
    private String chargeType;

    /**
     * 是否为交通费用  0 不是 1是
     */
    private Integer traffic;
    /**
     * 父节点id
     */
    private Long pid;
    /**
     * 名称
     */
    private String name;
    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 描述
     */
    private String description;

    /**
     * 收费寄读方式 全托，半托，走读 # 分割
     */
    private String boardingCodes;

    /**
     * 交通路线
     */
    private String routesCode;

    /**
     * 子节点
     */
    @TableField(exist = false)
    private List<ChargeDetailsModel> children;


}
