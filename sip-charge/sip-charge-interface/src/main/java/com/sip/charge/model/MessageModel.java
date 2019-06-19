package com.sip.charge.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sip.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_sip_charge_personnel_message")
public class MessageModel extends BaseModel {
    private Long operationId;
    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 发送目标
     */
    private String target;

    /**
     * 信息内容
     */
    private String content;
}
