package com.dmc.admin.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApiUserListRequestVo implements Serializable {
    private Long pageSize;

    private Long pageNum;

    private Long total;

    private List<ApiUserVo> list;

    public static class ApiUserVo{

    private Integer userId;

    /**
     * 姓
     */
    private String firstName;

    /**
     * 名
     */
    private String lastName;

    private String mobileNumber;

    private String email;
//  (1,primary owner 2,joint owner 3,staff)
    private Integer relation;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getRelation() {
            return relation;
        }

        public void setRelation(Integer relation) {
            this.relation = relation;
        }
    }
}
