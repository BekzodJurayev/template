package uz.furor.template.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBean {
    private String username;
    private String password;

    @Override
    public String toString() {
        return username;
    }
}
