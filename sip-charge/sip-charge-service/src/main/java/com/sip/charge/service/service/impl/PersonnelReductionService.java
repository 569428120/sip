package com.sip.charge.service.service.impl;

import com.sip.charge.model.PersonnelReductionModel;
import com.sip.charge.service.mapper.PersonnelReductionMapper;
import com.sip.charge.service.service.IPersonnelReductionService;
import com.sip.common.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class PersonnelReductionService extends BaseService<PersonnelReductionMapper, PersonnelReductionModel> implements IPersonnelReductionService {
}
