package com.dmc.service;

import com.dmc.config.ResponseResult;
import com.dmc.pojo.TbBusinessDocuments;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-29
 */
public interface ITbBusinessDocumentsService extends IService<TbBusinessDocuments> {

    /**
     * 根据文件地址，删除文件
     * @param fileUrl
     * @return
     */
    ResponseResult deleteByFile(String fileUrl);
}
