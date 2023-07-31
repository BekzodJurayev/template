package uz.furor.template.service.admin.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.furor.template.db.beans.admin.PermissionBean;
import uz.furor.template.db.beans.admin.RoleAccessBean;
import uz.furor.template.db.beans.admin.RoleBean;
import uz.furor.template.exceptions.RestException;
import uz.furor.template.mapper.admin.PermissionMapper;
import uz.furor.template.mapper.admin.RoleAccessMapper;
import uz.furor.template.mapper.admin.RoleMapper;
import uz.furor.template.service.admin.RoleService;
import uz.furor.template.utils.Utils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;
    private final RoleAccessMapper roleAccessMapper;

    @Override
    public RoleBean findRoleById(Integer id) {
        RoleBean role;
        try {
            role = roleMapper.selectRoles(Utils.generateMap("id", id)).get(0);
        } catch (Exception e) {
            throw RestException.restThrow("Role not found", HttpStatus.NOT_FOUND);
        }
        return role;
    }

    @Override
    public List<RoleBean> findRoles(Map<String, Object> params) {
        return roleMapper.selectRoles(params);
    }

    @Override
    public RoleBean insertRole(RoleBean roleBean) {
        return roleMapper.insertRole(roleBean);
    }

    @Override
    public RoleBean updateRole(RoleBean roleBean) {
        return roleMapper.updateRole(roleBean);
    }

    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteRole(Utils.generateMap("id", id));
    }

    @Override
    public RoleBean findWholeRoleById(Integer id) {
        Set<Integer> ids = new HashSet<>();
        ids.add(id);
        return findWholeById(id, ids);
    }

    @Override
    public List<RoleBean> findAllUserRolesByUserId(Integer userId) {
        List<RoleBean> roles = roleMapper.selectRoles(Utils.generateMap("users_id", userId));
        Set<Integer> ids = new HashSet<>();
        roles.forEach(role -> ids.add(role.getId()));
        roles.forEach(roleBean -> collectAllRoleFields(roleBean, ids));
        return roles;
    }

    @Override
    public List<RoleBean> findDefaultRoles() {
        List<RoleBean> roles = roleMapper.selectRoles(Utils.generateMap("is_default", true));
        Set<Integer> ids = new HashSet<>();
        roles.forEach(role -> ids.add(role.getId()));
        roles.forEach(roleBean -> collectAllRoleFields(roleBean, ids));
        return roles;
    }

    private RoleBean findWholeById(Integer id, Set<Integer> ids) {
        RoleBean roleBean = findRoleById(id);
        collectAllRoleFields(roleBean, ids);
        return roleBean;
    }

    private void collectAllRoleFields(RoleBean roleBean, Set<Integer> ids) {
        Map<String, Object> params = Utils.generateMap("roles_id", roleBean.getId());
        List<PermissionBean> permissions = permissionMapper.selectPermissions(params);
        roleBean.setPermissions(permissions);
        List<RoleAccessBean> roleAccesses = roleAccessMapper.selectRoleAccesses(params);
        roleAccesses.removeIf(role_access -> ids.contains(role_access.getAccessed_roles_id()));
        List<RoleBean> accessedRoles = new ArrayList<>(roleAccesses.size());
        for (RoleAccessBean accessedRole : roleAccesses)
            accessedRoles.add(findWholeById(accessedRole.getRoles_id(), ids));
        roleBean.setAccessedRoles(accessedRoles);
    }

}
