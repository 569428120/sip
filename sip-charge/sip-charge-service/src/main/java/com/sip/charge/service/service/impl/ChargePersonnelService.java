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
import com.sip.common.exception.SipException;
import com.sip.common.service.BaseService;
import com.sip.common.vo.PageResult;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
        Map<Long, List<ChargeDetailsModel>> reductionMap = new HashMap<>(chargePersonnelModels.size());
        if (chargeDetailsModels == null || chargeDetailsModels.isEmpty()) {
            return reductionMap;
        }
        // 转换成 寄读方式和收费项的map 以及路线的收费map
        Map<String, List<ChargeDetailsModel>> boardingMap = new HashMap<>(3);
        Map<String, List<ChargeDetailsModel>> routeMap = new HashMap<>(10);
        chargeDetailsModels.forEach(item -> {
            // 交通费用
            if (ChargeDetailsModel.TRAFFIC_TRUE.equals(item.getTraffic()) && StringUtils.isNotBlank(item.getRoutesCode())) {
                List<ChargeDetailsModel> routeCharges = routeMap.computeIfAbsent(item.getRoutesCode(), k -> new ArrayList<>(10));
                routeCharges.add(item);
                return;
            }
            // 寄读方式
            String boardingCodes = item.getBoardingCodes();
            if (StringUtils.isBlank(boardingCodes)) {
                return;
            }
            List<String> boardingCodeList = Arrays.asList(boardingCodes.split(","));
            boardingCodeList.forEach(code -> {
                List<ChargeDetailsModel> boardingCharges = boardingMap.computeIfAbsent(code, k -> new ArrayList<>(10));
                boardingCharges.add(item);
            });
        });

        chargePersonnelModels.forEach(item -> {
            List<ChargeDetailsModel> models = reductionMap.computeIfAbsent(item.getId(), k -> new ArrayList<>(10));
            // 需要乘车
            if (item.getRide()) {
                List<ChargeDetailsModel> routeCharges = routeMap.get(item.getRoutesCode());
                if (routeCharges != null) {
                    models.addAll(routeCharges);
                }
            }
            List<ChargeDetailsModel> boardingCharges = boardingMap.get(item.getBoardingCode());
            if (boardingCharges != null) {
                models.addAll(boardingCharges);
            }
        });

        return reductionMap;
    }

    /**
     * 金额统计数量
     *
     * @param chargePersonnelModels chargePersonnelModels
     * @return Map<Long, BigDecimal>
     */
    private Map<Long, BigDecimal> getChargeDetailsAmountSumMap(List<ChargePersonnelModel> chargePersonnelModels) {
        Map<Long, BigDecimal> amountMap = new HashMap<>(chargePersonnelModels.size());
        Map<Long, List<ChargeDetailsModel>> detailsMap = this.getChargeDetailsMap(chargePersonnelModels);
        if (detailsMap == null || detailsMap.isEmpty()) {
            return amountMap;
        }
        detailsMap.forEach((id, models) -> {
            BigDecimal amountSum = models.stream().map(ChargeDetailsModel::getAmount).reduce(BigDecimal::add).get();
            amountMap.put(id, amountSum);
        });
        return amountMap;
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
            return new PageResult<>(0L, Collections.emptyList());
        }

        List<Long> chargePersonnelIds = chargePersonnelModels.stream().map(ChargePersonnelModel::getId).collect(Collectors.toList());

        // 联系方式
        Map<Long, List<PersonnelContactModel>> contactMap = this.getPersonnelContactMap(chargePersonnelIds);

        // 减免信息
        Map<Long, List<PersonnelReductionModel>> reductionMap = this.getPersonnelReductionMap(chargePersonnelIds);

        // 费用信息
        Map<Long, BigDecimal> chargeDetailsMap = this.getChargeDetailsAmountSumMap(chargePersonnelModels);

        // 转换为VO
        List<ChargePersonnelVo> chargePersonnelVos = chargePersonnelModels.stream().map(item -> this.newChargePersonnelVo(item, contactMap, reductionMap, chargeDetailsMap)).collect(Collectors.toList());

        Long count = personnelMapper.getChargePersonnelModelsCount(personnelModel);
        return new PageResult<>(count, chargePersonnelVos);
    }


    /**
     * 创建vo
     *
     * @param model            model
     * @param contactMap       联系人
     * @param reductionMap     减免
     * @param chargeDetailsMap 收费项
     * @return ChargePersonnelVo
     */
    private ChargePersonnelVo newChargePersonnelVo(ChargePersonnelModel model, Map<Long, List<PersonnelContactModel>> contactMap,
                                                   Map<Long, List<PersonnelReductionModel>> reductionMap, Map<Long, BigDecimal> chargeDetailsMap) {
        ChargePersonnelVo vo = new ChargePersonnelVo();
        BeanUtils.copyProperties(model, vo);
        // 设置联系方式
        vo.setContacts(contactMap.get(vo.getId()));
        // 设置减免信息
        vo.setReductions(reductionMap.get(vo.getId()));
        // 金额
        vo.setAmountSum(chargeDetailsMap.get(vo.getId()));

        return vo;
    }


    /**
     * 获取人员vo对象
     *
     * @param personnelId 人员id
     * @return ChargePersonnelVo
     */
    @Override
    public ChargePersonnelVo getChargePersonnelVoById(Long personnelId) {
        ChargePersonnelModel chargePersonnelModel = this.selectById(personnelId);
        if (chargePersonnelModel == null) {
            log.error("chargePersonnelModel is null by personnelId {}", personnelId);
            throw new SipException("chargePersonnelModel is null by personnelId " + personnelId);
        }
        List<Long> chargePersonnelIds = new ArrayList<>(1);
        chargePersonnelIds.add(personnelId);
        List<ChargePersonnelModel> chargePersonnelModels = new ArrayList<>(1);
        chargePersonnelModels.add(chargePersonnelModel);

        // 联系方式
        Map<Long, List<PersonnelContactModel>> contactMap = this.getPersonnelContactMap(chargePersonnelIds);
        // 减免信息
        Map<Long, List<PersonnelReductionModel>> reductionMap = this.getPersonnelReductionMap(chargePersonnelIds);
        // 费用信息
        Map<Long, BigDecimal> chargeDetailsMap = this.getChargeDetailsAmountSumMap(chargePersonnelModels);

        return this.newChargePersonnelVo(chargePersonnelModel, contactMap, reductionMap, chargeDetailsMap);
    }

    /**
     * 删除
     *
     * @param personnelIds personnelIds
     */
    @Override
    public void deleteChargePersonnel(List<String> personnelIds) {
        if (personnelIds == null || personnelIds.isEmpty()) {
            log.info("personnelIds is null");
            return;
        }
        this.deleteByIds(personnelIds);
    }

    /**
     * 校验
     *
     * @param personnelModel personnelModel
     */
    @NonNull
    private void checkParams(@NonNull ChargePersonnelModel personnelModel) {
        // 学号不能重复
        List<ChargePersonnelModel> chargePersonnelModels = this.selectList(new QueryWrapper<ChargePersonnelModel>()
                .eq("student_id", personnelModel.getStudentId())
        );
        if (chargePersonnelModels != null && !chargePersonnelModels.isEmpty()) {
            throw new SipException("学号:" + personnelModel.getStudentId() + "已存在");
        }
    }

    /**
     * 跟新
     *
     * @param personnelModel personnelModel
     */
    @Override
    public void updatePersonnel(ChargePersonnelModel personnelModel) {
        this.checkParams(personnelModel);
        this.insert(personnelModel);
    }

    /**
     * 新增
     *
     * @param personnelModel personnelModel
     */
    @Override
    public void createPersonnel(ChargePersonnelModel personnelModel) {
        this.checkParams(personnelModel);
        this.updateById(personnelModel);
    }
}
