package com.sip.charge.service.service.impl;

import com.sip.charge.model.UserModel;
import com.sip.charge.service.mapper.UserMapper;
import com.sip.charge.service.service.IUserService;
import com.sip.common.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<UserMapper, UserModel> implements IUserService {
}
