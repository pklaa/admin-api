package com.dmc.admin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiDepotRequestVo implements Serializable {
    private Integer depotId;

    /**
     * 名称
     */
    private String storeName;

    public ApiDepotRequestVo(Integer depotId, String storeName) {
        this.depotId = depotId;
        this.storeName = storeName;
    }


}
