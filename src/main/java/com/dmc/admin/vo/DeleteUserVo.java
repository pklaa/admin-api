package com.dmc.admin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeleteUserVo implements Serializable {
    private Integer businessId;
    private Integer userId;
}
