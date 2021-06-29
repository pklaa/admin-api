package com.dmc.mapper;

import com.dmc.admin.vo.ApiFindByBusinessIdRequestVo;
import com.dmc.pojo.TbBusiness;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-29
 */
public interface TbBusinessMapper extends BaseMapper<TbBusiness> {

    /**
     * 根据businessId查询店铺在不同depot的customerId;
     * @param businessId
     * @return
     */
    @Select("select cu.stl_customer_id as customerId,de.id as depotId,de.`name` as depotName from tb_business_customer cu,tb_depot de \n" +
            "where cu.depot_id = de.id and cu.business_id = #{businessId}")
    List<ApiFindByBusinessIdRequestVo.ApiBusinessCustomer> selectCustomerList(@Param("businessId") Integer businessId);

}
