package uz.furor.template.service.admin.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.furor.template.db.beans.admin.RoleBean;
import uz.furor.template.repository.admin.PermissionRepository;
import uz.furor.template.repository.admin.RoleRepository;
import uz.furor.template.service.admin.RoleService;
import uz.furor.template.utils.Utils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    @Override
    public RoleBean findById(Integer id){
        return roleRepository.selectOne(id);
    }

    @Override
    public List<RoleBean> findAllUserRolesByUserId(Integer userId) {
        return roleRepository.selectList(Utils.generateMap("users_id", userId));
    }

    @Override
    public List<RoleBean> findDefaultRoles() {
        List<RoleBean> roles = roleRepository.selectList(Utils.generateMap("is_default", true));

        return roles;
    }


}
