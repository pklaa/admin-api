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
 * 内部人员表
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TbStaff extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    /**
     * 0: delete\n1: active
     */
    private Integer status;

    private Integer roleId;

    private Integer depotId;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;


}
