package com.sip.common.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {
    /**
     * id 自动增长
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 租户id
     */
    private Long tenantId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean logicDel;
}
