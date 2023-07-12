package uz.furor.template.db.beans.admin;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import uz.furor.template.db.beans.core.BaseNameBean;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionBean extends BaseNameBean implements GrantedAuthority {
    private String shortname;
    private boolean enabled;
    @Override
    public String getAuthority() {
        return String.valueOf(getId());
    }
}
