package com.sip.charge.model;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sip.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_sip_charge_project")
public class ChargeProjectModel extends BaseModel {
    /**
     * 项目名称
     */
    private String name;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;
    /**
     * 描述
     */
    private String description;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean logicDel;
}
