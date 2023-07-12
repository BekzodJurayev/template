package uz.furor.template.db.beans.core;

import lombok.*;

@Getter
@Setter
public class BaseNameBean extends BaseIdBean{
    private String name;
    private Integer order_id;
    private String description;
}
