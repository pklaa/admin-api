package com.dmc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmc.admin.vo.*;
import com.dmc.config.ResponseResult;
import com.dmc.config.Status;
import com.dmc.exception.CustomException;
import com.dmc.mapper.TbUserMapper;
import com.dmc.pojo.TbBusinessUser;
import com.dmc.mapper.TbBusinessUserMapper;
import com.dmc.pojo.TbUser;
import com.dmc.service.ITbBusinessUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
public class TbBusinessUserServiceImpl extends ServiceImpl<TbBusinessUserMapper, TbBusinessUser> implements ITbBusinessUserService {
    @Autowired
    private TbBusinessUserMapper businessUserMapper;

    @Autowired
    private TbUserMapper userMapper;


    @Override
    public ResponseResult getApiStoreAccessUser(ApiGetStoreAcccessUserVo vo) {
        Page<ApiUserListRequestVo.ApiUserVo> apiUserVoPage = new Page<>(vo.getPageNum(),vo.getPageSize());
        IPage<ApiUserListRequestVo.ApiUserVo> byList = businessUserMapper.findByList(apiUserVoPage, vo.getBusinessId());
        ApiUserListRequestVo apiUserListRequestVo = new ApiUserListRequestVo();
        apiUserListRequestVo.setList(byList.getRecords());
        BeanUtil.copyProperties(vo,apiUserListRequestVo);
        apiUserListRequestVo.setTotal(byList.getTotal());
        return ResponseResult.success(apiUserListRequestVo);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult addApiUserByOwner(AddUserByOwnerVo vo) {
        List<TbUser> tbUsers = userMapper.selectList(new QueryWrapper<TbUser>().lambda().eq(TbUser::getMobile, vo.getMobileNumber())
                .eq(TbUser::getIsDelete, 0));

        if(!CollectionUtil.isEmpty(tbUsers)){
            throw new CustomException(Status.USER_EXIST.code,Status.USER_EXIST.message);
        }

        TbUser tbUser = new TbUser();
        tbUser.setMobile(vo.getMobileNumber());
        tbUser.setFirstName(vo.getFirstName());
        tbUser.setLastName(vo.getLastName());
        tbUser.setEmail(vo.getEmail());
        tbUser.setCreateAt(LocalDateTime.now());
        tbUser.setUpdateAt(LocalDateTime.now());
        tbUser.setRegisterStatus(0);
        tbUser.setIsDelete(0);
        userMapper.insert(tbUser);

        TbBusinessUser businessUser = new TbBusinessUser();
        businessUser.setUserId(tbUser.getId());
        businessUser.setBusinessId(vo.getBusinessId());
        businessUser.setMobile(vo.getMobileNumber());
        businessUser.setFirstName(tbUser.getFirstName());
        businessUser.setLastName(tbUser.getLastName());
        businessUser.setRelation(vo.getRelation());
        businessUser.setCreateAt(LocalDateTime.now());
        businessUser.setUpdateAt(LocalDateTime.now());
        businessUser.setIsDelete(0);
        businessUserMapper.insert(businessUser);
        return ResponseResult.success("successfully added");

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult apiDeleteUser(DeleteUserVo vo) {

        //校验员工是否是此店员工
        List<TbBusinessUser> list1 = businessUserMapper.selectList(new QueryWrapper<TbBusinessUser>().lambda().eq(TbBusinessUser::getUserId, vo.getUserId())
                .eq(TbBusinessUser::getBusinessId, vo.getBusinessId())
                .eq(TbBusinessUser::getIsDelete, 0));
        if(CollectionUtil.isEmpty(list1)){
            throw new CustomException(Status.CLERK_NOT_EXIST.code,Status.CLERK_NOT_EXIST.message);
        }

        TbBusinessUser businessUser1 = list1.get(0);

        //删除该用户的信息（tb_user）
        TbUser tbUser = new TbUser();
        tbUser.setIsDelete(1);
        tbUser.setId(vo.getUserId());
        tbUser.setUpdateAt(LocalDateTime.now());
        userMapper.updateById(tbUser);

        //删除用户关系信息(tb_business_user)

        businessUser1.setIsDelete(1);
        businessUser1.setUpdateAt(LocalDateTime.now());
        businessUserMapper.updateById(businessUser1);

        //删除用户账户信息
//        TbAccount tbAccount = new TbAccount();
//        tbAccount.setUpdateAt(LocalDateTime.now());
//        tbAccount.setIsDelete(1);
//        accountMapper.update(tbAccount, new UpdateWrapper<TbAccount>().lambda().eq(TbAccount::getUserId,vo.getUserId()));

        return ResponseResult.success("successfully delete");

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult updateApiUserByOwner(ApiUpdateUserByOwnerVo vo) {
        //user修改
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",vo.getUserId());
        queryWrapper.eq("is_delete",0);
        TbUser tbUsers = userMapper.selectOne(queryWrapper);
        if(tbUsers == null){
            throw new CustomException(Status.DATA_ERROR.code,Status.DATA_ERROR.message);
        }

        tbUsers.setUpdateAt(LocalDateTime.now());
        tbUsers.setEmail(vo.getEmail());
        tbUsers.setFirstName(vo.getFirstName());
        tbUsers.setLastName(vo.getLastName());
        tbUsers.setMobile(vo.getMobileNumber());
        userMapper.updateById(tbUsers);

        //business_user
        TbBusinessUser businessUser = businessUserMapper.selectOne(new QueryWrapper<TbBusinessUser>().lambda().eq(TbBusinessUser::getUserId, vo.getUserId())
                .eq(TbBusinessUser::getBusinessId, vo.getBusinessId())
                .eq(TbBusinessUser::getIsDelete, 0));
        if(businessUser == null){
            throw new CustomException(Status.DATA_ERROR.code,Status.DATA_ERROR.message);
        }
        businessUser.setRelation(vo.getRelation());
        businessUser.setUpdateAt(LocalDateTime.now());
        businessUser.setLastName(vo.getLastName());
        businessUser.setFirstName(vo.getFirstName());
        businessUser.setMobile(vo.getMobileNumber());
        businessUserMapper.updateById(businessUser);
        return ResponseResult.success("successfully");
    }
}
