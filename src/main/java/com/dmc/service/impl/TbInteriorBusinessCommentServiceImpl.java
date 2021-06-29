package com.dmc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dmc.config.ResponseResult;
import com.dmc.config.Status;
import com.dmc.exception.CustomException;
import com.dmc.pojo.TbInteriorBusinessComment;
import com.dmc.mapper.TbInteriorBusinessCommentMapper;
import com.dmc.service.ITbInteriorBusinessCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-29
 */
@Service
public class TbInteriorBusinessCommentServiceImpl extends ServiceImpl<TbInteriorBusinessCommentMapper, TbInteriorBusinessComment> implements ITbInteriorBusinessCommentService {

    @Autowired
    private TbInteriorBusinessCommentMapper commentMapper;

    @Override
    public ResponseResult deleteByCommentId(Integer commentId) {
        TbInteriorBusinessComment tbInteriorBusinessComment = commentMapper.selectOne(new QueryWrapper<TbInteriorBusinessComment>().lambda()
                .eq(TbInteriorBusinessComment::getId, commentId));
        if(tbInteriorBusinessComment == null){
            throw new CustomException(Status.INFORMATION_DELETED.code,Status.INFORMATION_DELETED.message);
        }

        commentMapper.deleteById(commentId);
        return ResponseResult.success("delete success");

    }
}
