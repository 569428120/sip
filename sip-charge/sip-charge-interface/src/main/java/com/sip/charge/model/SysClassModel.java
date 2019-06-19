package com.sip.charge.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sip.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 班级
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_sip_sys_class")
public class SysClassModel extends BaseModel {
    /**
     * 年级
     */
    private String grade;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 班级编码
     */
    private String classCode;

}
