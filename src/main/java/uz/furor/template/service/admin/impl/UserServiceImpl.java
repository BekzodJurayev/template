package uz.furor.template.service.admin.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.furor.template.db.beans.admin.UserBean;
import uz.furor.template.exceptions.RestException;
import uz.furor.template.mapper.admin.UserMapper;
import uz.furor.template.service.admin.UserService;
import uz.furor.template.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserBean findById(Integer id) {
        List<UserBean> users = userMapper.selectUsers(Utils.generateMap("id", id));
        if (Objects.equals(users.size(), 1))
            return users.get(0);
        throw RestException.restThrow("User not found", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserBean findByUsername(String username) throws UsernameNotFoundException {
        List<UserBean> users = userMapper.selectUsers(Utils.generateMap("username", username));
        if (!Objects.equals(users.size(), 1))
            throw new UsernameNotFoundException("An user could not be found by this username");
        return users.get(0);
    }

    @Override
    public List<UserBean> selectList(Map<String, Object> params) {
        return userMapper.selectUsers(params);
    }

    @Override
    public UserBean save(UserBean user) {
        Map<String, Object> params = Utils.generateMap(
                "p_name", user.getName(),
                "p_username", user.getUsername(),
                "p_password", user.getPassword(),
                "p_password_expire_date", user.getPassword_expire_date(),
                "p_enabled", user.isEnabled(),
                "p_result", null,
                "p_new_id", null,
                "p_res_msg", null,
                "p_log_msg", null);
        userMapper.insertUser(params);
        params.put("id", params.get("p_new_id"));
        return userMapper.selectUsers(params).get(0);
    }
}
