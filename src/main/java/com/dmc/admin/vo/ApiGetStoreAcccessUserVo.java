package com.dmc.admin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiGetStoreAcccessUserVo implements Serializable {
    private Integer businessId;
    private Long pageSize;

    private Long pageNum;


}
