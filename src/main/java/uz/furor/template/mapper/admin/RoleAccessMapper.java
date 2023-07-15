package uz.furor.template.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import uz.furor.template.db.beans.admin.RoleAccessBean;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleAccessMapper {

    List<RoleAccessBean> selectRoleAccesses(Map<String, Object> params, RowBounds rowBounds);
    List<RoleAccessBean> selectRoleAccesses(Map<String, Object> params);
    RoleAccessBean insertRoleAccess(RoleAccessBean roleAccessBean);
    RoleAccessBean updateRoleAccess(RoleAccessBean roleAccessBean);
    void deleteRoleAccess(Map<String, Object> params);
}
