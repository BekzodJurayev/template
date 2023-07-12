package uz.furor.template.db.beans.admin;

import lombok.*;
import uz.furor.template.db.beans.core.BaseNameBean;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleBean extends BaseNameBean {
    private String shortname;
    private boolean is_default;
    private boolean enabled;
    private Set<PermissionBean> permissions;
    private Set<RoleBean> accessedRoles;
}
