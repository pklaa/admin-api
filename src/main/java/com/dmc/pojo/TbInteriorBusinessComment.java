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
public class TbInteriorBusinessComment extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer businessId;

    private String managerName;

    private String content;

    private String addTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDelete;


}
