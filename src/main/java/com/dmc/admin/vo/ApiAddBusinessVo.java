package com.dmc.admin.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiAddBusinessVo implements Serializable {
    @ApiModelProperty(value = "店铺名称")
    private String businessName;

    @ApiModelProperty(value = "店铺类型（0,Stopped,1，Active 2,Pending")
    private Integer status;

    @ApiModelProperty(value = "邮编码")
    private String postCode;

    @ApiModelProperty(value = "店铺地址")
    private String businessAddress;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "premise")
    private String premise;

    @ApiModelProperty(value = "firstName")
    private String firstName;

    @ApiModelProperty(value = "lastName")
    private String lastName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "电话")
    private String telephone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "注册店铺")
    private Integer depotId;

    @ApiModelProperty(value = "registerNo")
    private String registerNo;

    @ApiModelProperty(value = "vatNo")
    private String vatNo;

    @ApiModelProperty(value = "economic Operator ID")
    private String eoid;

    @ApiModelProperty(value = "Faclity ID")
    private String fid;

    @ApiModelProperty(value = "size of store")
    private String depotSize;

    @ApiModelProperty(value = "Member of symbol group")
    private String mosg;

    @ApiModelProperty(value = "businessCategory")
    private String businessCategory;

    @ApiModelProperty(value = "businessType")
    private String businessType;

    @ApiModelProperty(value = "companyDocuments")
    private Files[] files;

    @ApiModelProperty(value = "preference(1,telephone 2,post 3,sms 4,email) 入参形式id拼接成字符串（1,2,3,4类型）")
    private String preference;

    @ApiModelProperty(value = "customerTags")
    private String[] customerTags;

    @ApiModelProperty(value = "备注")
    private Comment[] comment;

    public static class Files{
        private Integer fileType;
        private String fileUrl;


        public Integer getFileType() {
            return fileType;
        }

        public void setFileType(Integer fileType) {
            this.fileType = fileType;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }
    }

    public static class Comment{
        private Integer id;
        private String content;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }



}
