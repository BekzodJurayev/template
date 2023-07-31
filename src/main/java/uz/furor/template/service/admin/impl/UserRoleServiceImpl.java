package uz.furor.template.service.admin.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.furor.template.db.beans.admin.RoleBean;
import uz.furor.template.db.beans.admin.UserRoleBean;
import uz.furor.template.mapper.admin.UserRoleMapper;
import uz.furor.template.service.admin.UserRoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleMapper userRoleMapper;

    @Override
    public void saveUserRoles(Integer users_id, List<RoleBean> roles) {
        roles.forEach(role -> userRoleMapper.insertUserRole(new UserRoleBean(users_id, role.getId())));
    }

    @Override
    public void saveUserRoles(List<Integer> roles_ids, Integer users_id) {
        roles_ids.forEach(roles_id -> userRoleMapper.insertUserRole(new UserRoleBean(users_id, roles_id)));
    }
}
