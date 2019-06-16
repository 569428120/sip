package com.sip.charge.service.service.impl;

import com.sip.charge.model.SysRouteModel;
import com.sip.charge.service.mapper.SysRouteMapper;
import com.sip.charge.service.service.ISysRouteService;
import com.sip.common.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SysRouteService extends BaseService<SysRouteMapper, SysRouteModel> implements ISysRouteService {
}
