package com.sip.charge.service.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sip.charge.model.ChargePersonnelModel;
import com.sip.charge.model.PersonnelContactModel;
import com.sip.charge.service.mapper.PersonnelContactMapper;
import com.sip.charge.service.service.IPersonnelContactService;
import com.sip.common.exception.SipException;
import com.sip.common.service.BaseService;
import com.sip.common.vo.PageResult;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class PersonnelContactService extends BaseService<PersonnelContactMapper, PersonnelContactModel> implements IPersonnelContactService {


    /**
     * 根据学号查询
     *
     * @param studentId studentId
     * @return List<PersonnelContactModel>
     */
    @Override
    public List<PersonnelContactModel> getPersonnelContactsByStudentId(@NonNull String studentId) {
        return this.selectList(new QueryWrapper<PersonnelContactModel>()
                .eq("personnel_id", studentId)
        );
    }

    /**
     * 删除
     *
     * @param contactIds contactIds
     */
    @Override
    public void deletePersonnelContacts(@NonNull List<String> contactIds) {
        this.delete(new UpdateWrapper<PersonnelContactModel>()
                .in("id", contactIds)
        );
    }

    /**
     * 跟新新增
     *
     * @param personnelIds  personnelIds
     * @param contactModels contactModels
     */
    @Override
    public void putPersonnelContacts(@NonNull List<String> personnelIds, @NonNull List<PersonnelContactModel> contactModels) {

        //存在id则更新
        List<PersonnelContactModel> updateModels = contactModels.stream().filter(item -> item.getId() != null).collect(Collectors.toList());
        List<Long> updateIds = updateModels.stream().map(PersonnelContactModel::getId).collect(Collectors.toList());

        // 删除
        this.delete(new UpdateWrapper<PersonnelContactModel>()
                .in("personnel_id", personnelIds)
                .notIn("id", updateIds)
        );
        // 更新
        this.updateBatchById(updateModels);

        //新增的
        List<PersonnelContactModel> insertModels = contactModels.stream().filter(item -> item.getId() == null).collect(Collectors.toList());
        List<PersonnelContactModel> newModels = new ArrayList<>(personnelIds.size() * insertModels.size());

        personnelIds.forEach(personnelId -> {
            insertModels.forEach(item -> {
                item.setPersonnelId(Long.parseLong(personnelId));
                newModels.add(item);
            });
        });

        if (!newModels.isEmpty()) {
            this.insertBatch(newModels);
        }


    }

    /**
     * 添加
     *
     * @param personnelId  personnelId
     * @param contactModel contactModel
     */
    @Override
    public void addPersonnelContactModel(Long personnelId, PersonnelContactModel contactModel) {
        if (personnelId == null || contactModel == null) {
            log.error("personnelId or contactModel is null");
            throw new SipException("personnelId or contactModel is null");
        }
        contactModel.setPersonnelId(personnelId);
        this.insert(contactModel);
    }
}
