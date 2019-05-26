package org.caps.myshop.commons.service.impl;

import org.caps.myshop.commons.domain.TbUser;
import org.caps.myshop.commons.mapper.TbUserMapper;
import org.caps.myshop.commons.service.TbUserService;
import org.springframework.stereotype.Service;

/**
 * @author caps
 * @Date 2019/5/25 22:34
 * @Description
 */
@Service
public class TbUserServiceImpl extends BaseCrudServiceImpl<TbUser, TbUserMapper> implements TbUserService {
}