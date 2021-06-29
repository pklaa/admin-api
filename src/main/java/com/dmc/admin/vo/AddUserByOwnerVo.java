package com.dmc.admin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddUserByOwnerVo implements Serializable {
    private Integer businessId;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String email;
    private Integer relation;
}
