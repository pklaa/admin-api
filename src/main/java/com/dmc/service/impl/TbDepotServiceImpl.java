package com.dmc.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dmc.admin.vo.ApiDepotRequestVo;
import com.dmc.config.ResponseResult;
import com.dmc.config.Status;
import com.dmc.exception.CustomException;
import com.dmc.pojo.TbDepot;
import com.dmc.mapper.TbDepotMapper;
import com.dmc.service.ITbDepotService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-29
 */
@Service
public class TbDepotServiceImpl extends ServiceImpl<TbDepotMapper, TbDepot> implements ITbDepotService {

    @Autowired
    private TbDepotMapper depotMapper;

    @Override
    public ResponseResult getApiAllDepot() {

        LambdaQueryWrapper<TbDepot> eq = new QueryWrapper<TbDepot>().lambda().eq(TbDepot::getIsDelete, 0);

        List<TbDepot> tbDepots = depotMapper.selectList(eq);

        if(CollectionUtil.isEmpty(tbDepots)){
            throw new CustomException(Status.DATA_ERROR.code,Status.DATA_ERROR.message);
        }

        List<ApiDepotRequestVo> list = new ArrayList<>();

        tbDepots.stream().forEach(a->{
            list.add(new ApiDepotRequestVo(a.getId(),a.getName()));
        });

        return ResponseResult.success(list);

    }
}
