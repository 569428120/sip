package com.sip.charge.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sip.charge.model.ChargePersonnelModel;
import com.sip.charge.model.PersonnelContactModel;
import com.sip.common.service.IBaseService;
import com.sip.common.vo.PageResult;

import java.util.List;

public interface IPersonnelContactService extends IBaseService<PersonnelContactModel> {

    /**
     * 根据学号查询
     *
     * @param studentId studentId
     * @return List<PersonnelContactModel>
     */
    List<PersonnelContactModel> getPersonnelContactsByStudentId(String studentId);

    /**
     * 删除
     *
     * @param contactIds contactIds
     */
    void deletePersonnelContacts(List<String> contactIds);

    /**
     * 跟新新增
     *
     * @param personnelIds  personnelIds
     * @param contactModels contactModels
     */
    void putPersonnelContacts(List<String> personnelIds, List<PersonnelContactModel> contactModels);

}
