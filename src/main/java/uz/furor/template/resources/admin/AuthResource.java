package uz.furor.template.resources.admin;


import org.springframework.http.ResponseEntity;
import uz.furor.template.db.beans.admin.UserBean;

public interface AuthResource {

    ResponseEntity<?> login(UserBean user);
}
