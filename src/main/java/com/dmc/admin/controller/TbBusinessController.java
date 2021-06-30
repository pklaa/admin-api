package com.dmc.admin.controller;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.dmc.admin.vo.*;
import com.dmc.config.ResponseResult;
import com.dmc.config.Status;
import com.dmc.exception.CustomException;
import com.dmc.service.*;
import com.dmc.utils.FileUtils;
import com.dmc.utils.RegexUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

/**
 * business管理
 *
 */
@RestController
@RequestMapping("/api-business")
@Api("api-business")
@CrossOrigin
public class TbBusinessController {
    private Logger logger = LoggerFactory.getLogger(TbBusinessController.class);

    @Autowired
    private ITbBusinessService businessService;

    @Autowired
    private ITbInteriorBusinessCommentService businessCommentService;

    @Autowired
    private ITbBusinessDocumentsService documentsService;

    @Autowired
    private ITbBusinessUserService tbBusinessUserService;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private ITbDepotService depotService;

    @PostMapping("/getDepotList")
    @ApiOperation(value = "获取所有店铺列表(添加)")
    public ResponseResult getDepotList(){
        return depotService.getApiAllDepot();
    }

    @PostMapping("/getBusinessList")
    @ApiOperation(value = "获取所有商店(首页)")
    public ResponseResult getBusinessList(@RequestBody ApiBusinessVo vo){

        if(vo == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, Status.PARAMETER_ERROR.message);
        }

        if(vo.getPageNum() == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The pageNum did not fill in  ");
        }

        if(vo.getPageSize() == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The pageSize did not fill in  ");
        }

        if(vo.getSearchFiled() == null && StringUtils.isEmpty(vo.getSearch()) && vo.getStatus() == null && vo.getDepotId() == null){
            return ResponseResult.success(new ArrayList<>());
        }

        return businessService.getBusinessList(vo);
    }

    @PostMapping("/addBusiness")
    @ApiOperation(value = "添加business")
    public ResponseResult addBusiness(@RequestBody ApiAddBusinessVo vo) throws IOException {
        if(vo == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, Status.PARAMETER_ERROR.message);
        }

        if(StringUtils.isEmpty(vo.getBusinessName())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The BusinessName did not fill in  ");
        }

        if(vo.getStatus() == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The status did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getPostCode())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The postCode did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getBusinessAddress())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The BusinessAddress did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getCountry())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The country did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getCity())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The city did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getPremise())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The premise did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getFirstName())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The firstName did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getLastName())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The lastName did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getMobile())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The mobile did not fill in  ");
        }

        if(!RegexUtils.isTel(vo.getMobile())){
            return new ResponseResult(Status.PHONE_ERROR.code,Status.PHONE_ERROR.message);
        }

        if(StringUtils.isEmpty(vo.getTelephone())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The telephone did not fill in  ");
        }

        if(!RegexUtils.isTel(vo.getTelephone())){
            return new ResponseResult(Status.PHONE_ERROR.code,Status.PHONE_ERROR.message);
        }

        if(StringUtils.isEmpty(vo.getEmail())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The email did not fill in  ");
        }

        if(!RegexUtils.isEmail(vo.getEmail())){
            return new ResponseResult(Status.EMAIL_ERROR.code,Status.EMAIL_ERROR.message);
        }

        if(vo.getDepotId() == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The depotId did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getRegisterNo())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The registerNo did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getVatNo())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The vatNo did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getEoid())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The EOID did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getFid())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The fid did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getDepotSize())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The depotSize did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getMosg())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The MOSG did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getBusinessCategory())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The businessCategory did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getBusinessType())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The businessType did not fill in  ");
        }


        if(StringUtils.isEmpty(vo.getPreference())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The preference did not fill in  ");
        }

        if(ArrayUtil.isEmpty(vo.getCustomerTags())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The customerTags did not fill in  ");
        }


        logger.info("请求进入");
        logger.info(vo.toString());
        return businessService.addBusiness(vo);



    }

    @PostMapping("/fileUpload")
    @ApiOperation(value = "文件上传")
    public ResponseResult fileUpload(MultipartFile file) throws IOException {
        if(file == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The file did not fill in  ");
        }
        String upload = fileUtils.upload(file);
        if(StringUtils.isEmpty(upload)){
            throw new CustomException(Status.IMAGE_UPLOAD_FAILED.code,Status.IMAGE_UPLOAD_FAILED.message);
        }
        return ResponseResult.success(upload);
    }

    @PostMapping("/findByBusinessId")
    @ApiOperation(value = "回显")
    public ResponseResult findByBusinessId(@RequestBody ApiFindByBusinessIdVo vo){
        if(vo == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, Status.PARAMETER_ERROR.message);
        }
        if(vo.getBusinessId() == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The businessId did not fill in  ");
        }
        return businessService.findByBusinessId(vo.getBusinessId());
    }

    @PostMapping("/deleteByCommentId")
    @ApiOperation(value = "删除备注")
    public ResponseResult deleteByCommentId(@RequestBody ApiRequestBodyVo vo){
        if(vo == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, Status.PARAMETER_ERROR.message);
        }
        if (vo.getId() == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The commentId did not fill in  ");

        }
        return businessCommentService.deleteByCommentId(vo.getId());
    }

    @PostMapping("/deleteByFile")
    @ApiOperation(value = "删除文件")
    public ResponseResult deleteByFile(@RequestBody ApiDeleteFileVo vo){
        if(vo == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, Status.PARAMETER_ERROR.message);
        }
        if (StringUtils.isEmpty(vo.getFileUrl())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The fileId did not fill in  ");
        }
        return documentsService.deleteByFile(vo.getFileUrl());
    }

    @PostMapping("/getStoreAccessUser")
    @ApiOperation(value = "员工列表")
    public ResponseResult getStoreAccessUser(@RequestBody ApiGetStoreAcccessUserVo vo){
        if(vo == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, Status.PARAMETER_ERROR.message);
        }
        if(vo.getBusinessId() == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code,"The businessId did not fill in  ");
        }
        if(vo.getPageNum() == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The pageNum did not fill in  ");
        }

        if(vo.getPageSize() == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The pageSize did not fill in  ");
        }
        return tbBusinessUserService.getApiStoreAccessUser(vo);
    }

    //添加员工
    @PostMapping("/addUserByOwner")
    @ApiOperation(value = "添加员工")
    public ResponseResult addUserByOwner(@RequestBody AddUserByOwnerVo vo){
        if(vo == null){
            throw new CustomException(Status.PARAMETER_ERROR.code,Status.PARAMETER_ERROR.message);
        }
        if(StringUtils.isEmpty(vo.getMobileNumber())){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The phone did not fill in  ");
        }
        if(!RegexUtils.isTel(vo.getMobileNumber())){
            throw new CustomException(Status.PHONE_ERROR.code,Status.PHONE_ERROR.message);
        }
        if(StringUtils.isEmpty(vo.getEmail())){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The email did not fill in  ");
        }
        if(!RegexUtils.isEmail(vo.getEmail())){
            throw new CustomException(Status.EMAIL_ERROR.code,Status.EMAIL_ERROR.message);
        }
        if(StringUtils.isEmpty(vo.getFirstName())){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The firstname did not fill in  ");
        }
        if(StringUtils.isEmpty(vo.getLastName())){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The lastname did not fill in  ");
        }
        if(vo.getBusinessId() == null){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The businessId did not fill in  ");
        }
        if(vo.getRelation() == null || vo.getRelation() == 1){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The relation did not fill in  ");
        }
        return tbBusinessUserService.addApiUserByOwner(vo);
    }

    //编辑员工信息
    @PostMapping("/updateUserByOwner")
    @ApiOperation(value = "编辑员工")
    public ResponseResult addUserByOwner(@RequestBody ApiUpdateUserByOwnerVo vo){
        if(vo == null){
            throw new CustomException(Status.PARAMETER_ERROR.code,Status.PARAMETER_ERROR.message);
        }
        if(vo.getUserId() == null){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The userId did not fill in  ");
        }
        if(StringUtils.isEmpty(vo.getMobileNumber())){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The phone did not fill in  ");
        }
        if(!RegexUtils.isTel(vo.getMobileNumber())){
            throw new CustomException(Status.PHONE_ERROR.code,Status.PHONE_ERROR.message);
        }
        if(StringUtils.isEmpty(vo.getEmail())){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The email did not fill in  ");
        }
        if(!RegexUtils.isEmail(vo.getEmail())){
            throw new CustomException(Status.EMAIL_ERROR.code,Status.EMAIL_ERROR.message);
        }
        if(StringUtils.isEmpty(vo.getFirstName())){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The firstname did not fill in  ");
        }
        if(StringUtils.isEmpty(vo.getLastName())){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The lastname did not fill in  ");
        }
        if(vo.getBusinessId() == null){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The businessId did not fill in  ");
        }
        if(vo.getRelation() == null || vo.getRelation() == 1){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The relation did not fill in  ");
        }
        return tbBusinessUserService.updateApiUserByOwner(vo);
    }


    //删除员工信息
    @PostMapping("/deleteUser")
    @ApiOperation("删除员工")
    public ResponseResult deleteUser(@RequestBody DeleteUserVo vo){

        if(vo == null){
            throw new CustomException(Status.PARAMETER_ERROR.code,Status.PARAMETER_ERROR.message);
        }

        if(vo.getBusinessId() == null){
            throw new CustomException(Status.PARAMETER_ERROR.code,"The businessId did not fill in  ");
        }


        return tbBusinessUserService.apiDeleteUser(vo);
    }

    @PostMapping("/updateBusiness")
    @ApiOperation(value = "编辑business")
    public ResponseResult updateBusiness(@RequestBody ApiUpdateBusinessVo vo) throws IOException {
        if(vo == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, Status.PARAMETER_ERROR.message);
        }

        if(vo.getBusinessId() == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The businessId did not fill in  ");

        }

        if(StringUtils.isEmpty(vo.getBusinessName())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The BusinessName did not fill in  ");
        }

        if(vo.getStatus() == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The status did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getPostCode())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The postCode did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getBusinessAddress())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The BusinessAddress did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getCountry())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The country did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getCity())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The city did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getPremise())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The premise did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getFirstName())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The firstName did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getLastName())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The lastName did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getMobile())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The mobile did not fill in  ");
        }

        if(!RegexUtils.isTel(vo.getMobile())){
            return new ResponseResult(Status.PHONE_ERROR.code,Status.PHONE_ERROR.message);
        }

        if(StringUtils.isEmpty(vo.getTelephone())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The telephone did not fill in  ");
        }

        if(!RegexUtils.isTel(vo.getTelephone())){
            return new ResponseResult(Status.PHONE_ERROR.code,Status.PHONE_ERROR.message);
        }

        if(StringUtils.isEmpty(vo.getEmail())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The email did not fill in  ");
        }

        if(!RegexUtils.isEmail(vo.getEmail())){
            return new ResponseResult(Status.EMAIL_ERROR.code,Status.EMAIL_ERROR.message);
        }

        if(vo.getDepotId() == null){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The depotId did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getRegisterNo())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The registerNo did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getVatNo())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The vatNo did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getEoid())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The EOID did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getFid())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The fid did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getDepotSize())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The depotSize did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getMosg())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The MOSG did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getBusinessCategory())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The businessCategory did not fill in  ");
        }

        if(StringUtils.isEmpty(vo.getBusinessType())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The businessType did not fill in  ");
        }


        if(StringUtils.isEmpty(vo.getPreference())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The preference did not fill in  ");
        }

        if(ArrayUtil.isEmpty(vo.getCustomerTags())){
            return new ResponseResult(Status.PARAMETER_ERROR.code, "The customerTags did not fill in  ");
        }


        logger.info("请求进入");
        logger.info(vo.toString());
        return businessService.updateBusiness(vo);
    }

}
