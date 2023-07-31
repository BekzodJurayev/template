package uz.furor.template.db.beans.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.furor.template.db.beans.core.BaseIdBean;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName(value = "user")
public class UserBean extends BaseIdBean implements UserDetails {
    private String name;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Date password_expire_date;
    private boolean enabled;
    private List<RoleBean> roles;
    private Map<Integer, PermissionBean> permissions;
    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.values();
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return password_expire_date.after(new Date());
    }

    @Override
    public String toString() {
        return " { username: " + username + " } ";
    }
}
