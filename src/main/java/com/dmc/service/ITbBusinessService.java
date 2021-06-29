package com.dmc.service;

import com.dmc.admin.vo.ApiAddBusinessVo;
import com.dmc.admin.vo.ApiBusinessVo;
import com.dmc.admin.vo.ApiUpdateBusinessVo;
import com.dmc.config.ResponseResult;
import com.dmc.pojo.TbBusiness;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-29
 */
public interface ITbBusinessService extends IService<TbBusiness> {

    /**
     * 获取店铺列表
     * @param vo
     * @return
     */
    ResponseResult getBusinessList(ApiBusinessVo vo);

    /**
     * 添加business
     * @param vo
     * @return
     */
    ResponseResult addBusiness(ApiAddBusinessVo vo);

    /**
     * 回显
     * 根据businessId查询business相关信息
     * @param businessId
     * @return
     */
    ResponseResult findByBusinessId(Integer businessId);

    /**
     * 编辑店铺信息
     * @param vo
     * @return
     */
    ResponseResult updateBusiness(ApiUpdateBusinessVo vo);
}
