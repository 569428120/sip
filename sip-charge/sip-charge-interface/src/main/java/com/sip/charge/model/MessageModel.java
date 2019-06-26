package com.sip.charge.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sip.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_sip_charge_personnel_message")
public class MessageModel extends BaseModel {

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 人员id
     */
    private Long personnelId;

    /**
     * 操作人
     */
    private Long operationId;
    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 发送时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date sendTime;

    /**
     * 发送目标
     */
    private String target;


    /**
     * 标题
     */
    private String title;

    /**
     * 信息内容
     */
    private String content;
}
