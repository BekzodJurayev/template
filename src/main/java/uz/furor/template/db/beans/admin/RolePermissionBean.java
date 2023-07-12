package uz.furor.template.db.beans.admin;

import lombok.*;
import uz.furor.template.db.beans.core.BaseIdBean;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionBean extends BaseIdBean {
    private Integer roles_id;
    private Integer permissions_id;
}
