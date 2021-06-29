package com.dmc.admin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiAddRequestFileVo implements Serializable {

    private Integer id;
    private Integer fileType;
    private String fileName;
    private String managerName;
    private String addTime;
    private String fileUrl;

}
