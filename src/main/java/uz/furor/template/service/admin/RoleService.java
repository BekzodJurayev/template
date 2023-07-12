package uz.furor.template.service.admin;

import uz.furor.template.db.beans.admin.RoleBean;

import java.util.List;

public interface RoleService {
    RoleBean findById(Integer id);
    List<RoleBean> findAllUserRolesByUserId(Integer userId);

    List<RoleBean> findDefaultRoles();
}
