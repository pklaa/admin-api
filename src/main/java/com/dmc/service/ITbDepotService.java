package com.dmc.service;

import com.dmc.config.ResponseResult;
import com.dmc.pojo.TbDepot;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-29
 */
public interface ITbDepotService extends IService<TbDepot> {

    /**
     * 10家店铺信息
     * @return
     */
    ResponseResult getApiAllDepot();
}
