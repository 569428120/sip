package com.sip.charge.service.service;

import com.sip.charge.model.ChargePersonnelModel;
import com.sip.charge.vo.ChargePersonnelVo;
import com.sip.common.service.IBaseService;
import com.sip.common.vo.PageResult;

import java.util.List;

public interface IChargePersonnelService extends IBaseService<ChargePersonnelModel> {


    /**
     * 分页查询
     *
     * @param projectModel 搜索添加
     * @param page         当前页
     * @param pageSize     每页显示的数量
     * @return PageResult
     */
    PageResult<ChargePersonnelVo> getChargePersonnelVoListPage(ChargePersonnelModel projectModel, Integer page, Integer pageSize);

    /**
     * 获取人员vo对象
     *
     * @param personnelId 人员id
     * @return ChargePersonnelVo
     */
    ChargePersonnelVo getChargePersonnelVoById(Long personnelId);

    /**
     * 删除
     *
     * @param personnelIds personnelIds
     */
    void deleteChargePersonnel(List<String> personnelIds);

    /**
     * 跟新
     *
     * @param personnelModel personnelModel
     */
    void updatePersonnel(ChargePersonnelModel personnelModel);

    /**
     * 新增
     *
     * @param personnelModel personnelModel
     */
    void createPersonnel(ChargePersonnelModel personnelModel);
}
