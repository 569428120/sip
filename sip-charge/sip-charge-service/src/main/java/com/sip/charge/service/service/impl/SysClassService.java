package com.sip.charge.service.service.impl;

import com.sip.charge.model.SysClassModel;
import com.sip.charge.service.mapper.SysClassMapper;
import com.sip.charge.service.service.ISysClassService;
import com.sip.common.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysClassService extends BaseService<SysClassMapper, SysClassModel> implements ISysClassService {
}
