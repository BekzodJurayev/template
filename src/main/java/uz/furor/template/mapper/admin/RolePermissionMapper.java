package uz.furor.template.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import uz.furor.template.db.beans.admin.RolePermissionBean;

import java.util.List;
import java.util.Map;

@Mapper
public interface RolePermissionMapper {
    List<RolePermissionBean> selectRolePermissions(Map<String, Object> params, RowBounds rowBounds);
    List<RolePermissionBean> selectRolePermissions(Map<String, Object> params);
    RolePermissionBean insertRolePermission(RolePermissionBean rolePermissionBean);
    RolePermissionBean updateRolePermission(RolePermissionBean rolePermissionBean);
    void deleteRolePermission(Map<String, Object> params);
}
