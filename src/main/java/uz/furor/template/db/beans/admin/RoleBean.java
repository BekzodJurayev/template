package uz.furor.template.db.beans.admin;

import lombok.*;
import uz.furor.template.db.beans.core.BaseNameBean;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleBean extends BaseNameBean {
    private boolean is_default = false;
    private boolean enabled = false;
    private List<PermissionBean> permissions;
    private List<RoleBean> accessedRoles;
}
