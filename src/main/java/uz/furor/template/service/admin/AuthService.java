package uz.furor.template.service.admin;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.furor.template.db.beans.admin.UserBean;
import uz.furor.template.exceptions.RestException;
import uz.furor.template.models.LoginBean;
import uz.furor.template.models.RegisterBean;

public interface AuthService {
    String login(LoginBean login) throws UsernameNotFoundException;
    String register(RegisterBean register) throws RestException;
}
