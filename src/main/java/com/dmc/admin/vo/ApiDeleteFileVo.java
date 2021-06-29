package com.dmc.admin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiDeleteFileVo implements Serializable {
    private String fileUrl;
}
