package com.dmc.admin.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApiFindByBusinessIdRequestVo implements Serializable {
    private Integer businessId;

    private String businessName;

    //(value = "店铺类型（0,Stopped,1，Active 2,Pending")
    private Integer status;

    private String postCode;

    private String businessAddress;

    private String country;

    private String city;

    private String premise;

    private String firstName;

    private String lastName;

    private String mobile;

    private String telephone;

    private String email;

    private Integer depotId;

    private String registerNo;

    private String vatNo;

    private String eoid;

    private String fid;

    private String depotSize;

    private String mosg;

    private String businessCategory;

    private String businessType;

    private List<ApiAddRequestFileVo> files;

    //(value = "preference(1,telephone 2,post 3,sms 4,email) 入参形式id拼接成字符串（1,2,3,4类型）")
    private String preference;

//    @ApiModelProperty(value = "customerTags")
    private String[] customerTags;

    private List<ApiCommentRequestVo> comment;

    private List<ApiBusinessCustomer> customers;

    public static class ApiBusinessCustomer{
        private Integer customerId;
        private Integer depotId;
        private String depotName;

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public Integer getDepotId() {
            return depotId;
        }

        public void setDepotId(Integer depotId) {
            this.depotId = depotId;
        }

        public String getDepotName() {
            return depotName;
        }

        public void setDepotName(String depotName) {
            this.depotName = depotName;
        }
    }



}
