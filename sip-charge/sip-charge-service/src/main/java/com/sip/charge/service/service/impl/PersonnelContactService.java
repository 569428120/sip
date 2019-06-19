package com.sip.charge.service.service.impl;


import com.sip.charge.model.PersonnelContactModel;
import com.sip.charge.service.mapper.PersonnelContactMapper;
import com.sip.charge.service.service.IPersonnelContactService;
import com.sip.common.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class PersonnelContactService extends BaseService<PersonnelContactMapper, PersonnelContactModel> implements IPersonnelContactService {
}
