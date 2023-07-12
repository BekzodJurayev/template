package uz.furor.template.service.admin;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.furor.template.db.beans.admin.UserBean;
import uz.furor.template.exceptions.RestException;

public interface AuthService {
    String register(UserBean userBean) throws RestException;
    String login(UserBean userBean) throws UsernameNotFoundException;
}
