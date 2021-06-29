package com.dmc.admin.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApiBusinessRequestVo implements Serializable {

    private Long pageSize;

    private Long pageNum;

    private Long total;

    private List<BusinessRequestVo> list;

    public static class BusinessRequestVo {
        private Integer id;

        private Integer primaryDepot;

        private String businessName;

        private String telephone;

        private String companyRegno;

        private Integer status;

        private Integer businessId;

        public Integer getBusinessId() {
            return businessId;
        }

        public void setBusinessId(Integer businessId) {
            this.businessId = businessId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getPrimaryDepot() {
            return primaryDepot;
        }

        public void setPrimaryDepot(Integer primaryDepot) {
            this.primaryDepot = primaryDepot;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getCompanyRegno() {
            return companyRegno;
        }

        public void setCompanyRegno(String companyRegno) {
            this.companyRegno = companyRegno;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }


}
