package uz.furor.template.service.admin;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.furor.template.db.beans.admin.UserBean;

public interface UserService {
    UserBean findById(Integer id) throws NotFoundException;
    UserBean findByUsername(String username) throws UsernameNotFoundException;

    UserBean save(UserBean user);
}
