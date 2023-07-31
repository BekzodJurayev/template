package uz.furor.template.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import uz.furor.template.db.beans.admin.UserRoleBean;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserRoleMapper {
    List<UserRoleBean> selectUserRoles(Map<String, Object> params, RowBounds rowBounds);
    List<UserRoleBean> selectUserRoles(Map<String, Object> params);
    void insertUserRole(UserRoleBean userRoleBean);
    void deleteUserRole(Map<String, Object> params);
}
