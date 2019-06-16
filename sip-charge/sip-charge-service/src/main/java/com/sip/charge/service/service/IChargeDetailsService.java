package com.sip.charge.service.service;

import com.sip.charge.model.ChargeDetailsModel;
import com.sip.common.service.IBaseService;

import java.util.List;

public interface IChargeDetailsService extends IBaseService<ChargeDetailsModel> {


    /**
     * 查询收费详情
     *
     * @param projectId 收费项目id
     * @param name      名称
     * @return List<ChargeDetailsModel>
     */
    List<ChargeDetailsModel> getTreeList(Long projectId, String name);

    /**
     * 更新
     *
     * @param chargeDetailsModel 数据
     */
    void updateDetails(ChargeDetailsModel chargeDetailsModel);


    /**
     * 创建收费项
     *
     * @param chargeDetailsModel 数据
     */
    void createProject(ChargeDetailsModel chargeDetailsModel);
}
