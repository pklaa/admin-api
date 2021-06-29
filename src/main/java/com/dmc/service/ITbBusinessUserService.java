package com.dmc.service;

import com.dmc.admin.vo.AddUserByOwnerVo;
import com.dmc.admin.vo.ApiGetStoreAcccessUserVo;
import com.dmc.admin.vo.ApiUpdateUserByOwnerVo;
import com.dmc.admin.vo.DeleteUserVo;
import com.dmc.config.ResponseResult;
import com.dmc.pojo.TbBusinessUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-29
 */
public interface ITbBusinessUserService extends IService<TbBusinessUser> {

    /**
     * 查询员工列表
     * @param vo
     * @return
     */
    ResponseResult getApiStoreAccessUser(ApiGetStoreAcccessUserVo vo);

    /**
     * 添加员工
     * @param vo
     * @return
     */
    ResponseResult addApiUserByOwner(AddUserByOwnerVo vo);

    /**
     * 编辑员工信息
     * @param vo
     * @return
     */
    ResponseResult updateApiUserByOwner(ApiUpdateUserByOwnerVo vo);

    /**
     * 删除员工信息
     * @param vo
     * @return
     */
    ResponseResult apiDeleteUser(DeleteUserVo vo);
}
