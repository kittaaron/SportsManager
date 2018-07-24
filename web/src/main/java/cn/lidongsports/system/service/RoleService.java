package cn.lidongsports.system.service;

import cn.lidongsports.core.service.DefaultEntityManager;
import cn.lidongsports.system.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService extends DefaultEntityManager<Role, Integer> {

}
