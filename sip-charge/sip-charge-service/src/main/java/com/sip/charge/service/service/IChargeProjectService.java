package com.sip.charge.service.service;

import com.sip.charge.model.ChargeProjectModel;
import com.sip.common.service.IBaseService;
import com.sip.common.vo.PageResult;

public interface IChargeProjectService extends IBaseService<ChargeProjectModel> {
    /**
     * 分页查询
     *
     * @param projectModel 过滤条件
     * @param page         当前页
     * @param pageSize     每页显示的数据量
     * @return PageResult
     */
    PageResult getListPage(ChargeProjectModel projectModel, Integer page, Integer pageSize);

    /**
     * 更新数据
     *
     * @param projectModel 数据
     */
    void updateProject(ChargeProjectModel projectModel);

    /**
     *  增加项目
     * @param projectModel 数据
     */
    void createProject(ChargeProjectModel projectModel);
}
