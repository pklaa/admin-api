package com.dmc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dmc.config.ResponseResult;
import com.dmc.config.Status;
import com.dmc.exception.CustomException;
import com.dmc.pojo.TbBusinessDocuments;
import com.dmc.mapper.TbBusinessDocumentsMapper;
import com.dmc.service.ITbBusinessDocumentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmc.utils.FileUtils;
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
public class TbBusinessDocumentsServiceImpl extends ServiceImpl<TbBusinessDocumentsMapper, TbBusinessDocuments> implements ITbBusinessDocumentsService {
    @Autowired
    private TbBusinessDocumentsMapper documentsMapper;
    @Autowired
    private FileUtils fileUtils;
    @Override
    public ResponseResult deleteByFile(String fileUrl) {
        String fileName = fileUrl.split("/")[fileUrl.split("/").length - 1];

        TbBusinessDocuments businessDocuments = documentsMapper.selectOne(new QueryWrapper<TbBusinessDocuments>().lambda()
                .eq(TbBusinessDocuments::getFileName, fileName));


        Boolean delete = fileUtils.delete(fileName);
        if(!delete){
            throw new CustomException(Status.FILE_DELETE_ERROR.code,Status.FILE_DELETE_ERROR.message);
        }

        if(businessDocuments != null){
            documentsMapper.deleteById(fileName);
        }


        return ResponseResult.success("delete success");

    }
}
