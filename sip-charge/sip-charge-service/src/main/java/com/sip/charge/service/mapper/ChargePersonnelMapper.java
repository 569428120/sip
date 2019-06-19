package com.sip.charge.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sip.charge.model.ChargePersonnelModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargePersonnelMapper extends BaseMapper<ChargePersonnelModel> {
    /**
     * 分页查询
     *
     * @param projectModel 过滤条件
     * @param page         当前页
     * @param pageSize     每页大小
     * @return List<ChargePersonnelModel>
     */
    List<ChargePersonnelModel> getChargePersonnelModelsPage(ChargePersonnelModel projectModel, Integer page, Integer pageSize);

    /**
     *  总数
     * @param projectModel
     * @return
     */
    Long getChargePersonnelModelsCount(ChargePersonnelModel projectModel);

}
