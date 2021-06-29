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
public class TbBusinessDocuments extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String fileName;

    /**
     * Mime type
     */
    private String mimeType;

    /**
     * 1: company document
2: personal id:
3: vat ID
4: EOID
5: FID
6: Other
     */
    private Integer category;

    private Integer businessId;

    /**
     * Create by staff id
     */
    private Integer createBy;

    /**
     * Update by staff id
     */
    private Integer updateBy;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;


}
