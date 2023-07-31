package uz.furor.template.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterBean {
    private String name;
    private String username;
    private String password;
    private String confirmPassword;
    @Override
    public String toString() {
        return username;
    }
}
