package uz.furor.template.mapper.admin;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.session.RowBounds;
import uz.furor.template.db.beans.admin.UserBean;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    List<UserBean> selectUsers(Map<String, Object> params, RowBounds rowBounds);

    List<UserBean> selectUsers(Map<String, Object> params);

    void insertUser(Map<String, Object> params);

    void updateUser(Map<String, Object> params);
}
