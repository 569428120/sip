package com.sip.charge.service.service.impl;

import com.sip.charge.model.SysBoardingTypeModel;
import com.sip.charge.service.mapper.SysBoardingTypeMapper;
import com.sip.charge.service.service.ISysBoardingTypeService;
import com.sip.common.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysBoardingTypeService extends BaseService<SysBoardingTypeMapper, SysBoardingTypeModel> implements ISysBoardingTypeService {
}
