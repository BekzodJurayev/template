package uz.furor.template.db.beans.admin;

import lombok.*;
import uz.furor.template.db.beans.core.BaseIdBean;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleAccessBean extends BaseIdBean {
    private Integer roles_id;
    private Integer accessed_roles_id;
}
