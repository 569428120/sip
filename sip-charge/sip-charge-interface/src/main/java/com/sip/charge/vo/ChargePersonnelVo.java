package com.sip.charge.vo;

import com.sip.charge.model.ChargePersonnelModel;
import com.sip.charge.model.PersonnelContactModel;
import com.sip.charge.model.PersonnelReductionModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
public class ChargePersonnelVo extends ChargePersonnelModel {
    /**
     * 班级名称
     */
    private String className;

    /**
     * 总收费l
     */
    private BigDecimal amountSum;

    /**
     * 联系人
     */
    private List<PersonnelContactModel> contacts;

    /**
     * 减免
     */
    private List<PersonnelReductionModel> reductions;

}
