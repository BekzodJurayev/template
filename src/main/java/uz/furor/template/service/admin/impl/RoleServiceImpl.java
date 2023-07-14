package uz.furor.template.service.admin.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.furor.template.db.beans.admin.RoleBean;
import uz.furor.template.mapper.admin.PermissionMapper;
import uz.furor.template.mapper.admin.RoleMapper;
import uz.furor.template.service.admin.RoleService;
import uz.furor.template.utils.Utils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;
    @Override
    public RoleBean findById(Integer id){
        List<RoleBean> roles = roleMapper.selectRoles(Utils.generateMap("id",id));
        return roles.get(0);
    }

    @Override
    public List<RoleBean> findAllUserRolesByUserId(Integer userId) {
        return roleMapper.selectRoles(Utils.generateMap("users_id", userId));
    }

    @Override
    public List<RoleBean> findDefaultRoles() {
        List<RoleBean> roles = roleMapper.selectRoles(Utils.generateMap("is_default", true));

        return roles;
    }


}
