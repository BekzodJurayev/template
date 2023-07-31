package uz.furor.template.resources.admin;


import org.springframework.http.ResponseEntity;
import uz.furor.template.models.LoginBean;
import uz.furor.template.models.RegisterBean;

public interface AuthResource {

    ResponseEntity<String> login(LoginBean login);
    ResponseEntity<String> register(RegisterBean register);
}
