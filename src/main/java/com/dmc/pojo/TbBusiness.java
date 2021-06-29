package com.dmc.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TbBusiness extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer cid;

    private String businessName;

    private String postcode;

    private String address1;

    private String address2;

    private String address3;

    private String address4;

    private String companyRegno;

    private String vatNo;

    @TableField("EOID")
    private String eoid;

    @TableField("FID")
    private String fid;

    @TableField("IOU")
    private String iou;

    @TableField("IOULIMIT")
    private Integer ioulimit;

    private String telephone;

    private String email;

    private Integer primaryDepot;

    private Integer status;

    private String premise;

    private BigDecimal currentIou;

    private String vatExampt;

    private String indicator;

    private String memberSymbolgroup;

    private String businessType;

    private String businessDepotSize;

    private String customerTags;

    private String businessCategory;

    private String priceScheme;

    private LocalDateTime recordAt;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private Integer isDelete;


}
