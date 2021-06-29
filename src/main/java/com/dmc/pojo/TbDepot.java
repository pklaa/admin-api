package com.dmc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class TbDepot extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 商店id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 联系电话
     */
    private String modile;

    /**
     * 邮编
     */
    private String postcode;

    /**
     * 简介
     */
    private String bried;

    /**
     * 省
     */
    private String address1;

    /**
     * 市
     */
    private String address2;

    /**
     * 区
     */
    private String address3;

    private String latitude;

    private String longitude;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private Integer isDelete;


}
