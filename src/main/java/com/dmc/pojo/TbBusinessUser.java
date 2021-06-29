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
public class TbBusinessUser extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 客户用户关系表主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 姓
     */
    private String firstName;

    /**
     * 名
     */
    private String lastName;

    /**
     * 商户id
     */
    private Integer businessId;

    /**
     * 关系状态
     */
    private Integer relation;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 修改时间
     */
    private LocalDateTime updateAt;

    private Integer isDelete;


}
