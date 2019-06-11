package com.sip.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sip.common.exception.SipException;
import com.sip.common.model.BaseModel;
import com.sip.common.vo.PageResult;

import java.util.List;


public class BaseService<M extends BaseMapper<T>, T extends BaseModel> extends ServiceImpl<M, T> implements IBaseService<T> {

    private void initTenant(QueryWrapper wrapper) {
        wrapper.eq("tenant_id", 111);
    }

    private void initTenant(T model) {
        model.setTenantId(111L);
    }

    private void initTenant(List<T> models) {
        models.forEach(item -> item.setTenantId(111L));
    }


    @Override
    public List<T> selectList(QueryWrapper wrapper) {
        this.initTenant(wrapper);
        return this.list(wrapper);
    }

    @Override
    public void insertBatch(List<T> models) {
        if (models == null || models.isEmpty()) {
            return;
        }
        this.initTenant(models);
        this.saveBatch(models);

    }

    /**
     * 根据ID查询
     *
     * @param id id
     * @return T
     */
    @Override
    public T selectById(Long id) {
        return this.getById(id);
    }

    /**
     * 根据id
     *
     * @param id id
     */
    @Override
    public void deleteById(Long id) {
        this.deleteById(id);
    }

    /**
     * 插入数据
     *
     * @param model 数据
     */
    @Override
    public void insert(T model) {
        this.initTenant(model);
        this.save(model);
    }

    /**
     * 分页查询
     *
     * @param page    分页对象
     * @param wrapper 过滤条件
     * @return PageResult
     */
    @Override
    public PageResult<T> selectPage(Page<T> page, Wrapper<T> wrapper) {
        IPage<T> iPage = this.baseMapper.selectPage(page, wrapper);
        if (iPage == null) {
            throw new SipException("selectPage error");
        }
        return new PageResult<T>(iPage.getTotal(), (int) iPage.getPages(), iPage.getRecords());
    }


}
