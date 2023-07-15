package uz.furor.template.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import uz.furor.template.db.beans.admin.PermissionBean;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionMapper {
    List<PermissionBean> selectPermissions(Map<String, Object> params, RowBounds rowBounds);
    List<PermissionBean> selectPermissions(Map<String, Object> params);
    void insertPermission(PermissionBean permissionBean);
    void updatePermission(PermissionBean permissionBean);
    void deletePermission(Map<String, Object> params);
}
