package com.sip.charge.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sip.charge.model.ChargeDetailsModel;
import com.sip.charge.model.ChargePersonnelModel;
import com.sip.charge.model.PersonnelContactModel;
import com.sip.charge.model.PersonnelReductionModel;
import com.sip.charge.service.mapper.ChargePersonnelMapper;
import com.sip.charge.service.service.IChargeDetailsService;
import com.sip.charge.service.service.IChargePersonnelService;
import com.sip.charge.service.service.IPersonnelContactService;
import com.sip.charge.service.service.IPersonnelReductionService;
import com.sip.charge.vo.ChargePersonnelVo;
import com.sip.common.service.BaseService;
import com.sip.common.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ChargePersonnelService extends BaseService<ChargePersonnelMapper, ChargePersonnelModel> implements IChargePersonnelService {

    @Resource
    private ChargePersonnelMapper personnelMapper;

    @Resource
    private IPersonnelContactService personnelContactService;

    @Resource
    private IPersonnelReductionService personnelReductionService;

    @Resource
    private IChargeDetailsService chargeDetailsService;


    /**
     * 获得联系方式
     *
     * @param chargePersonnelIds chargePersonnelIds
     * @return Map<String, List < PersonnelContactModel>>
     */
    private Map<Long, List<PersonnelContactModel>> getPersonnelContactMap(List<Long> chargePersonnelIds) {

        //  查询联系方式
        List<PersonnelContactModel> personnelContactModels = personnelContactService.list(new QueryWrapper<PersonnelContactModel>()
                .in("personnel_id", chargePersonnelIds)
        );
        Map<Long, List<PersonnelContactModel>> contactMap = new HashMap<>(chargePersonnelIds.size());
        if (personnelContactModels == null || personnelContactModels.isEmpty()) {
            return contactMap;
        }
        personnelContactModels.forEach(item -> {
            List<PersonnelContactModel> contactModels = contactMap.computeIfAbsent(item.getPersonnelId(), k -> new ArrayList<>(5));
            contactModels.add(item);
        });

        return contactMap;
    }

    /**
     * 减免信息
     *
     * @param chargePersonnelIds chargePersonnelIds
     * @return Map<Long, List < PersonnelReductionModel>>
     */
    private Map<Long, List<PersonnelReductionModel>> getPersonnelReductionMap(List<Long> chargePersonnelIds) {
        List<PersonnelReductionModel> personnelReductionModels = personnelReductionService.list(new QueryWrapper<PersonnelReductionModel>()
                .in("personnel_id", chargePersonnelIds)
        );
        Map<Long, List<PersonnelReductionModel>> reductionMap = new HashMap<>(chargePersonnelIds.size());
        if (personnelReductionModels == null || personnelReductionModels.isEmpty()) {
            return reductionMap;
        }

        personnelReductionModels.forEach(item -> {
            List<PersonnelReductionModel> reductionModels = reductionMap.computeIfAbsent(item.getPersonnelId(), k -> new ArrayList<>(5));
            reductionModels.add(item);
        });

        return reductionMap;
    }

    /**
     * 收费信息
     *
     * @param chargePersonnelModels chargePersonnelModels
     * @return Map<Long, List < ChargeDetailsModel>>
     */
    private Map<Long, List<ChargeDetailsModel>> getChargeDetailsMap(List<ChargePersonnelModel> chargePersonnelModels) {
        List<ChargeDetailsModel> chargeDetailsModels = chargeDetailsService.selectList(new QueryWrapper<>());
        return null;
    }

    /**
     * 分页查询
     *
     * @param personnelModel 搜索添加
     * @param page           当前页
     * @param pageSize       每页显示的数量
     * @return PageResult
     */
    @Override
    public PageResult<ChargePersonnelVo> getChargePersonnelVoListPage(ChargePersonnelModel personnelModel, Integer page, Integer pageSize) {

        List<ChargePersonnelModel> chargePersonnelModels = personnelMapper.getChargePersonnelModelsPage(personnelModel, page, pageSize);
        if (chargePersonnelModels == null || chargePersonnelModels.isEmpty()) {
            log.info("chargePersonnelModels is null");
            return null;
        }

        List<Long> chargePersonnelIds = chargePersonnelModels.stream().map(ChargePersonnelModel::getId).collect(Collectors.toList());

        // 联系方式
        Map<Long, List<PersonnelContactModel>> contactMap = this.getPersonnelContactMap(chargePersonnelIds);

        // 减免信息
        Map<Long, List<PersonnelReductionModel>> reductionMap = this.getPersonnelReductionMap(chargePersonnelIds);

        // 费用信息
        Map<Long, List<ChargeDetailsModel>> chargeDetailsMap = this.getChargeDetailsMap(chargePersonnelModels);

        // 转换为VO
        List<ChargePersonnelVo> chargePersonnelVos = chargePersonnelModels.stream().map(item -> {
            ChargePersonnelVo vo = new ChargePersonnelVo();
            BeanUtils.copyProperties(item, vo);
            // 设置联系方式
            vo.setContacts(contactMap.get(vo.getId()));
            // 设置减免信息
            vo.setReductions(reductionMap.get(vo.getId()));
            return vo;
        }).collect(Collectors.toList());

        Long count = personnelMapper.getChargePersonnelModelsCount(personnelModel);
        return new PageResult<>(count, chargePersonnelVos);
    }


    /**
     * 获取人员vo对象
     *
     * @param personnelId 人员id
     * @return ChargePersonnelVo
     */
    @Override
    public ChargePersonnelVo getChargePersonnelVoById(Long personnelId) {
        return null;
    }

    /**
     * 删除
     *
     * @param personnelIds personnelIds
     */
    @Override
    public void deleteChargePersonnel(List<String> personnelIds) {

    }

    /**
     * 跟新
     *
     * @param personnelModel personnelModel
     */
    @Override
    public void updatePersonnel(ChargePersonnelModel personnelModel) {

    }

    /**
     * 新增
     *
     * @param personnelModel personnelModel
     */
    @Override
    public void createPersonnel(ChargePersonnelModel personnelModel) {

    }
}
