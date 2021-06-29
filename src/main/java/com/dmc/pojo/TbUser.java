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
public class TbUser extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号
     */
    private String mobile;

    private String password;

    /**
     * 姓
     */
    private String firstName;

    private String lastName;

    /**
     * 邮箱
     */
    private String email;

    private String mktPrefer;

    /**
     * 设备id
     */
    private String deviceId;

    private Integer lastBusinessId;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private Integer registerStatus;

    private Integer isDelete;


}
