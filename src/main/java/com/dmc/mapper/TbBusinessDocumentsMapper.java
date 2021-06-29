package com.dmc.mapper;

import com.dmc.admin.vo.ApiAddRequestFileVo;
import com.dmc.pojo.TbBusinessDocuments;
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
public interface TbBusinessDocumentsMapper extends BaseMapper<TbBusinessDocuments> {
    /**
     * 根据businessId查询相关的文件信息
     * @param businessId
     * @return
     */
    @Select("select cu.id as id,cu.category as fileType,cu.file_name as fileName,cu.update_at as addTime,st.username as managerName from tb_business_documents cu,tb_staff st WHERE cu.business_id = #{businessId} and cu.create_by = st.id order by cu.category asc")
    public List<ApiAddRequestFileVo> findByBusinessId(@Param("businessId")Integer businessId);
}
