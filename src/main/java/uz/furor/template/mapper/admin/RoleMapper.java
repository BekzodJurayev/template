package uz.furor.template.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import uz.furor.template.db.beans.admin.RoleBean;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {
    List<RoleBean> selectRoles(Map<String, Object> params);
    List<RoleBean> selectRoles(Map<String, Object> params, RowBounds rowBounds);
    RoleBean insertRole(RoleBean roleBean);
    RoleBean updateRole(RoleBean roleBean);
    void deleteRole(Map<String, Object> params);
}
