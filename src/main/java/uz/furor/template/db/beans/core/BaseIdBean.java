package uz.furor.template.db.beans.core;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class BaseIdBean extends Bean {
    private Integer id;
    private Integer created_users_id;
    private Date created_date;
    private Integer updated_users_id;
    private Date updated_date;
}
