package com.dmc.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiBusinessVo implements Serializable {
        @ApiModelProperty(value = "值")
        private String search;
        @ApiModelProperty(value = "查询对象")
        private Integer searchFiled;
        @ApiModelProperty(value = "店铺Id")
        private Integer depotId;
        @ApiModelProperty(value = "店铺状态")
        private Integer status;
        @ApiModelProperty(value = "当前页")
        private Long pageNum;
        @ApiModelProperty(value = "每页大小")
        private Long pageSize;

}
