package uz.furor.template.service.admin;

import uz.furor.template.db.beans.admin.RoleBean;
import uz.furor.template.db.beans.admin.UserRoleBean;

import java.util.List;

public interface UserRoleService {
    void saveUserRoles(Integer users_id, List<RoleBean> roles);
    void saveUserRoles( List<Integer> roles_ids, Integer users_id);
}
