package com.dmc.service;

import com.dmc.config.ResponseResult;
import com.dmc.pojo.TbInteriorBusinessComment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-29
 */
public interface ITbInteriorBusinessCommentService extends IService<TbInteriorBusinessComment> {

    /**
     * 根据id删除备注
     * @param id
     * @return
     */
    ResponseResult deleteByCommentId(Integer id);
}
