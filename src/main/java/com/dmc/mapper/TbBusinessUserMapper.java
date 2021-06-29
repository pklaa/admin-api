package com.dmc.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmc.admin.vo.ApiUserListRequestVo;
import com.dmc.pojo.TbBusinessUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-29
 */
public interface TbBusinessUserMapper extends BaseMapper<TbBusinessUser> {

    /**
     * 根据businessId查询其旗下员工信息
     * @param apiUserVoPage
     * @param businessId
     * @return
     */
    @Select("select bu.user_id,us.first_name,us.last_name,us.mobile as mobileNumber,us.email,bu.relation from tb_user us,tb_business_user bu where\n" +
            "us.id = bu.user_id and bu.is_delete = 0 and \n" +
            "bu.relation !=1 and \n" +
            "bu.business_id = #{businessId}")
    IPage<ApiUserListRequestVo.ApiUserVo> findByList(Page<ApiUserListRequestVo.ApiUserVo> apiUserVoPage, @Param("businessId") Integer businessId);
}
