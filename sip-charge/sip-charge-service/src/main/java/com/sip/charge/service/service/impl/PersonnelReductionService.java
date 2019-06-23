package com.sip.charge.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sip.charge.model.PersonnelContactModel;
import com.sip.charge.model.PersonnelReductionModel;
import com.sip.charge.service.mapper.PersonnelReductionMapper;
import com.sip.charge.service.service.IPersonnelReductionService;
import com.sip.common.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class PersonnelReductionService extends BaseService<PersonnelReductionMapper, PersonnelReductionModel> implements IPersonnelReductionService {
    /**
     * 查询
     *
     * @param personnelId personnelId
     * @return List<PersonnelReductionModel>
     */
    @Override
    public List<PersonnelReductionModel> getPersonnelReductionsByStudentId(String personnelId) {
        return this.selectList(new QueryWrapper<PersonnelReductionModel>()
                .eq("personnel_id", personnelId)
        );
    }

    /**
     * 删除
     *
     * @param reductionIds reductionIds
     */
    @Override
    public void deletePersonnelReductions(List<String> reductionIds) {
        this.delete(new UpdateWrapper<PersonnelReductionModel>()
                .in("id", reductionIds)
        );
    }

    /**
     * 更新或新增
     *
     * @param personnelIds    personnelIds
     * @param reductionModels reductionModels
     */
    @Override
    public void putPersonnelReductions(List<String> personnelIds, List<PersonnelReductionModel> reductionModels) {
        //存在id则更新
        List<PersonnelReductionModel> updateModels = reductionModels.stream().filter(item -> item.getId() != null).collect(Collectors.toList());
        List<Long> updateIds = updateModels.stream().map(PersonnelReductionModel::getId).collect(Collectors.toList());

        // 删除
        this.delete(new UpdateWrapper<PersonnelReductionModel>()
                .in("personnel_id", personnelIds)
                .notIn("id", updateIds)
        );
        // 更新
        this.updateBatchById(updateModels);

        //新增的
        List<PersonnelReductionModel> insertModels = reductionModels.stream().filter(item -> item.getId() == null).collect(Collectors.toList());
        List<PersonnelReductionModel> newModels = new ArrayList<>(personnelIds.size() * insertModels.size());

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
}
