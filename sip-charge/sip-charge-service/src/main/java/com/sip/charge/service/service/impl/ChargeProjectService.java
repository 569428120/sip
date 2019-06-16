package com.sip.charge.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sip.charge.model.ChargeProjectModel;
import com.sip.charge.service.mapper.ChargeProjectMapper;
import com.sip.charge.service.service.IChargeProjectService;
import com.sip.common.exception.SipException;
import com.sip.common.service.BaseService;
import com.sip.common.vo.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ChargeProjectService extends BaseService<ChargeProjectMapper, ChargeProjectModel> implements IChargeProjectService {
    /**
     * 分页查询
     *
     * @param projectModel 过滤条件
     * @param page         当前页
     * @param pageSize     每页显示的数据量
     * @return PageResult
     */
    @Transactional(readOnly = true)
    @Override
    public PageResult getListPage(ChargeProjectModel projectModel, Integer page, Integer pageSize) {
        return this.selectPage(new Page<ChargeProjectModel>(page, pageSize), new QueryWrapper<ChargeProjectModel>()
                .like(StringUtils.isNotBlank(projectModel.getName()), "name", projectModel.getName())
                .ge(projectModel.getStartTime() != null, "start_time", projectModel.getStartTime())
                .le(projectModel.getEndTime() != null, "end_time", projectModel.getEndTime())
        );

    }

    /**
     * 更新数据
     *
     * @param projectModel 数据
     */
    @Override
    public void updateProject(ChargeProjectModel projectModel) {
        Long id = projectModel.getId();
        if (id == null) {
            return;
        }
        ChargeProjectModel oldModel = this.selectById(id);
        if (oldModel == null) {
            throw new SipException(id + " ,no data found");
        }
        oldModel.setName(projectModel.getName());
        oldModel.setStartTime(projectModel.getStartTime());
        oldModel.setEndTime(projectModel.getEndTime());
        oldModel.setDescription(projectModel.getDescription());

        this.updateById(oldModel);
    }

    /**
     * 增加项目
     *
     * @param projectModel 数据
     */
    @Override
    public void createProject(ChargeProjectModel projectModel) {
        this.insert(projectModel);
    }
}
