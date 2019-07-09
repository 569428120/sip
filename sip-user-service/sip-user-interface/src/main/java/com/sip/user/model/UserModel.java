package com.sip.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sip.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_sip_charge_details")
public class UserModel extends BaseModel {
    private String userName;
    private String password;

}
