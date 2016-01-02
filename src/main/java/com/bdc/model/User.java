package com.bdc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2015/12/13.
 * model domain data entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends BaseModel {
    private Integer id;
    private String username;
    private String password;
}
