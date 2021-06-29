package com.dmc.admin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiCommentRequestVo implements Serializable {
    private Integer id;
    private String managerName;
    private String addTime;
    private String content;
}
