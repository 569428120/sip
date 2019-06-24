package com.sip.charge.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sip.charge.model.PersonnelContactModel;
import com.sip.charge.model.PersonnelReductionModel;
import com.sip.common.service.IBaseService;

import java.util.List;

public interface IPersonnelReductionService extends IBaseService<PersonnelReductionModel> {
    /**
     * 查询
     *
     * @param personnelId personnelId
     * @return List<PersonnelReductionModel>
     */
    List<PersonnelReductionModel> getPersonnelReductionsByStudentId(String personnelId);

    /**
     * 删除
     *
     * @param reductionIds reductionIds
     */
    void deletePersonnelReductions(List<String> reductionIds);

    /**
     * 更新或新增
     *
     * @param personnelIds  personnelIds
     * @param reductionModels reductionModels
     */
    void putPersonnelReductions(List<String> personnelIds, List<PersonnelReductionModel> reductionModels);

    /**
     *    增加
     * @param personnelIds personnelIds
     * @param reductionModel reductionModel
     */
    void addPersonnelReductions(List<String> personnelIds, PersonnelReductionModel reductionModel);
}
