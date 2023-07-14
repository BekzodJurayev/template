package uz.furor.template.mapper.admin;

import org.apache.ibatis.session.RowBounds;
import uz.furor.template.db.beans.admin.RoleAccessBean;

import java.util.List;
import java.util.Map;

public interface RoleAccessMapper {

    List<RoleAccessBean> selectRoleAccesses(Map<String, Object> params, RowBounds rowBounds);
    List<RoleAccessBean> selectRoleAccesses(Map<String, Object> params);
}
