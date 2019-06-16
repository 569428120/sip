package com.sip.charge.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sip.charge.model.ChargeDetailsModel;
import com.sip.charge.service.mapper.ChargeDetailsMapper;
import com.sip.charge.service.service.IChargeDetailsService;
import com.sip.common.exception.SipException;
import com.sip.common.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ChargeDetailsService extends BaseService<ChargeDetailsMapper, ChargeDetailsModel> implements IChargeDetailsService {


    /**
     * 查询收费详情
     *
     * @param projectId 收费项目id
     * @param name      名称
     * @return List<ChargeDetailsModel>
     */
    @Transactional(readOnly = true)
    @Override
    public List<ChargeDetailsModel> getTreeList(Long projectId, String name) {
        List<ChargeDetailsModel> chargeDetailsModels = this.selectList(new QueryWrapper<ChargeDetailsModel>()
                .eq("project_id", projectId)
                .like(StringUtils.isNotBlank(name), "name", name)
        );

        if (chargeDetailsModels == null || chargeDetailsModels.isEmpty()) {
            log.info("chargeDetailsModels is null");
            return Collections.emptyList();
        }

        // 使用到的所有id
        Set<Long> ids = chargeDetailsModels.stream().map(ChargeDetailsModel::getId).collect(Collectors.toSet());
        Set<Long> queryPids = chargeDetailsModels.stream()
                .filter(item -> item.getPid() != null && !ids.contains(item.getPid()))
                .map(ChargeDetailsModel::getPid).collect(Collectors.toSet());

        List<ChargeDetailsModel> pidDetailsModels = null;
        if (!queryPids.isEmpty()) {
            pidDetailsModels = this.selectList(new QueryWrapper<ChargeDetailsModel>()
                    .in("id", queryPids)
            );
        }
        if (pidDetailsModels != null && !pidDetailsModels.isEmpty()) {
            chargeDetailsModels.addAll(pidDetailsModels);
        }

        // 整成树结构
        List<ChargeDetailsModel> fatherChargeDetailsModels = new ArrayList<>(10);
        Map<Long, List<ChargeDetailsModel>> childrenMap = new HashMap<>(chargeDetailsModels.size());
        chargeDetailsModels.forEach(item -> {
            // 父节点
            if (item.getPid() == null) {
                fatherChargeDetailsModels.add(item);
                return;
            }
            // 子节点处理方法
            List<ChargeDetailsModel> models = childrenMap.computeIfAbsent(item.getPid(), k -> new ArrayList<>(10));
            models.add(item);
        });

        if (fatherChargeDetailsModels.isEmpty()) {
            log.info("fatherChargeDetailsModels is null");
            return Collections.emptyList();
        }

        fatherChargeDetailsModels.forEach(item -> {
            List<ChargeDetailsModel> children = childrenMap.get(item.getId());
            //统计金额
            if (children == null || children.isEmpty()) {
                return;
            }
            BigDecimal sumAmount = children.stream().map(ChargeDetailsModel::getAmount).reduce(BigDecimal::add).get();
            item.setAmount(sumAmount);
            item.setChildren(children);
        });

        return fatherChargeDetailsModels;
    }

    /**
     * 更新
     *
     * @param chargeDetailsModel chargeDetailsModel
     */
    @Override
    public void updateDetails(ChargeDetailsModel chargeDetailsModel) {
        if (chargeDetailsModel == null || chargeDetailsModel.getId() == null) {
            log.error("id is null");
            throw new SipException("id is null");
        }
        ChargeDetailsModel oldModel = this.selectById(chargeDetailsModel.getId());
        if (oldModel == null) {
            throw new SipException(chargeDetailsModel.getId() + " ,no data found");
        }
        oldModel.setName(chargeDetailsModel.getName());
        oldModel.setRoutesCode(chargeDetailsModel.getRoutesCode());
        oldModel.setAmount(chargeDetailsModel.getAmount());
        oldModel.setDescription(chargeDetailsModel.getDescription());
        this.updateById(oldModel);
    }

    /**
     * 创建收费项
     *
     * @param chargeDetailsModel 数据
     */
    @Override
    public void createProject(ChargeDetailsModel chargeDetailsModel) {
        if (StringUtils.isBlank(chargeDetailsModel.getChargeType())) {
            log.error("chargeType is null");
            throw new SipException("收费类型不能为空");
        }
        if (chargeDetailsModel.getProjectId() == null) {
            log.error("ProjectId is null");
            throw new SipException("ProjectId is null");
        }
        // 收费项 pid不能为空
        if (ChargeDetailsModel.CHARGE_TYPE_ITEM.equals(chargeDetailsModel.getChargeType()) && chargeDetailsModel.getPid() == null) {
            log.error("pid is null");
            throw new SipException("pid is null");
        }
        ChargeDetailsModel newModel = new ChargeDetailsModel();
        newModel.setProjectId(chargeDetailsModel.getProjectId());
        newModel.setName(chargeDetailsModel.getName());
        newModel.setChargeType(chargeDetailsModel.getChargeType());
        newModel.setAmount(chargeDetailsModel.getAmount());
        newModel.setDescription(chargeDetailsModel.getDescription());
        // 如果为收费项则继承父类的属性
        if (ChargeDetailsModel.CHARGE_TYPE_ITEM.equals(chargeDetailsModel.getChargeType())) {
            ChargeDetailsModel pModel = this.selectById(chargeDetailsModel.getPid());
            if (pModel == null) {
                log.error("pModel is null");
                throw new SipException("pModel is null");
            }
            newModel.setPid(pModel.getId());
            newModel.setTraffic(pModel.getTraffic());
            newModel.setBoardingCodes(pModel.getBoardingCodes());
            newModel.setRoutesCode(pModel.getRoutesCode());
        } else {
            newModel.setTraffic(chargeDetailsModel.getTraffic());
            newModel.setBoardingCodes(chargeDetailsModel.getBoardingCodes());
            newModel.setRoutesCode(chargeDetailsModel.getRoutesCode());
        }

        this.insert(newModel);
    }
}
