package uz.furor.template.db.beans.admin;

import lombok.*;
import uz.furor.template.db.beans.core.BaseNameBean;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleBean extends BaseNameBean {
    private Integer users_id;
    private Integer roles_id;
}
