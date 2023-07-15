package uz.furor.template.service.admin;

import uz.furor.template.db.beans.admin.RoleBean;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

public interface RoleService {
    RoleBean findRoleById(Integer id);
    List<RoleBean> findRoles(Map<String, Object> params);
    RoleBean insertRole(RoleBean roleBean);
    RoleBean updateRole(RoleBean roleBean);
    void deleteRole(Integer id);
    RoleBean findWholeRoleById(Integer id);
    List<RoleBean> findAllUserRolesByUserId(Integer userId);

    List<RoleBean> findDefaultRoles();
}
