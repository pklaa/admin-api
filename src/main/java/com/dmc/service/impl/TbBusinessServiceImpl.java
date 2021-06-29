package com.dmc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmc.admin.vo.*;
import com.dmc.config.ResponseResult;
import com.dmc.config.Status;
import com.dmc.exception.CustomException;
import com.dmc.mapper.*;
import com.dmc.pojo.*;
import com.dmc.service.ITbBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmc.utils.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class TbBusinessServiceImpl extends ServiceImpl<TbBusinessMapper, TbBusiness> implements ITbBusinessService {
    private Logger logger = LoggerFactory.getLogger(TbBusinessServiceImpl.class);

    @Value("${dhamecha.path}")
    private String ip;

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private TbBusinessMapper businessMapper;

    @Autowired
    private TbBusinessUserServiceImpl businessUserService;

    @Autowired
    private TbBusinessCustomerMapper businessCustomerMapper;

    @Autowired
    private TbBusinessUserMapper businessUserMapper;

    @Autowired
    private TbBusinessDocumentsMapper businessDocumentsMapper;

    @Autowired
    private TbStaffMapper staffMapper;

    @Autowired
    private TbInteriorBusinessCommentMapper commentMapper;


    @Override
    public ResponseResult getBusinessList(ApiBusinessVo vo) {

        TbBusinessCustomer businessCustomer = new TbBusinessCustomer();
        ApiBusinessRequestVo requestVo = new ApiBusinessRequestVo();
        BeanUtil.copyProperties(vo,requestVo);

        if(vo.getSearchFiled() != null && !StringUtils.isEmpty(vo.getSearch())){
            if(vo.getSearchFiled() == 4){
                LambdaQueryWrapper<TbBusinessCustomer> lambda2 = new QueryWrapper<TbBusinessCustomer>().lambda();
                lambda2.eq(TbBusinessCustomer::getStlCustomerId,vo.getSearch()).eq(TbBusinessCustomer::getDepotId,1);
                businessCustomer = businessCustomerMapper.selectOne(lambda2);
                if(businessCustomer == null){
                    requestVo.setTotal(0l);
                    return ResponseResult.success(requestVo);
                }
            }
        }

        IPage<TbBusiness> page = new Page<>(vo.getPageNum(),vo.getPageSize());
        LambdaQueryWrapper<TbBusiness> lambda = new QueryWrapper<TbBusiness>().lambda();
        lambda.eq(TbBusiness::getIsDelete,0);

        if(vo.getDepotId() != null){
            lambda.eq(TbBusiness::getPrimaryDepot,vo.getDepotId());
        }

        if(vo.getStatus() != null){
            lambda.eq(TbBusiness::getStatus,vo.getStatus());
        }

        if(!StringUtils.isEmpty(vo.getSearch()) && vo.getSearchFiled()!=null){

            if(vo.getSearchFiled() == 1){
                lambda.eq(TbBusiness::getTelephone,vo.getSearch().trim());
            }

            if(vo.getSearchFiled() == 2){
                lambda.eq(TbBusiness::getCompanyRegno,vo.getSearch());
            }

            if(vo.getSearchFiled() == 3){
                lambda.like(TbBusiness::getBusinessName,vo.getSearch());
            }

            if(vo.getSearchFiled() == 4){
                lambda.eq(TbBusiness::getId,businessCustomer.getBusinessId());
            }

        }

        IPage<TbBusiness> tbBusinessIPage = businessMapper.selectPage(page, lambda);

        if(CollectionUtil.isEmpty(tbBusinessIPage.getRecords())){
            requestVo.setTotal(0l);
            return ResponseResult.success(requestVo);
        }

        List<ApiBusinessRequestVo.BusinessRequestVo> businessRequestVos = new ArrayList<>();
        for (TbBusiness a : tbBusinessIPage.getRecords()) {

            LambdaQueryWrapper<TbBusinessCustomer> lambda1 = new QueryWrapper<TbBusinessCustomer>().lambda();
            lambda1.eq(TbBusinessCustomer::getBusinessId,a.getId());
            lambda1.eq(TbBusinessCustomer::getDepotId,1);
            TbBusinessCustomer tbBusinessCustomer = businessCustomerMapper.selectOne(lambda1);
            if(tbBusinessCustomer == null){
                requestVo.setTotal(0l);
                return ResponseResult.success(requestVo);
            }
            ApiBusinessRequestVo.BusinessRequestVo businessRequestVo = new ApiBusinessRequestVo.BusinessRequestVo();
            BeanUtil.copyProperties(a,businessRequestVo);
            businessRequestVo.setId(tbBusinessCustomer.getStlCustomerId());
            businessRequestVo.setBusinessId(a.getId());
            businessRequestVos.add(businessRequestVo);
        }
        requestVo.setList(businessRequestVos);
        requestVo.setTotal(tbBusinessIPage.getTotal());

        return ResponseResult.success(requestVo);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult addBusiness(ApiAddBusinessVo vo) {
        TbUser tbUser = new TbUser();

        LambdaQueryWrapper<TbBusiness> and = new QueryWrapper<TbBusiness>().lambda().eq(TbBusiness::getIsDelete, 0)
                .and(wrapper -> wrapper
                        .eq(TbBusiness::getCompanyRegno, vo.getRegisterNo())
                        .or().eq(TbBusiness::getVatNo, vo.getVatNo())
                        .or().eq(TbBusiness::getEoid, vo.getEoid())
                        .or().eq(TbBusiness::getFid, vo.getFid()));
        List<TbBusiness> businessList = businessMapper.selectList(and);
        if(!CollectionUtil.isEmpty(businessList)){
            throw new CustomException(Status.STORE_REGISTERED.code,Status.STORE_REGISTERED.message);
        }
        logger.info("判断此人是否已经拥有一家店铺");
        tbUser = userMapper.selectOne(new QueryWrapper<TbUser>().lambda().eq(TbUser::getMobile, vo.getMobile())
                .eq(TbUser::getIsDelete, 0));

        TbBusinessCustomer tbBusinessCustomer = businessCustomerMapper.selectOne(new QueryWrapper<TbBusinessCustomer>().lambda().orderByDesc(TbBusinessCustomer::getStlCustomerId).last("limit 1"));

        logger.info("添加tb_business");
        TbBusiness tbBusiness = new TbBusiness();
        BeanUtil.copyProperties(vo,tbBusiness);
        tbBusiness.setCid(tbBusinessCustomer.getCid()+1);
        tbBusiness.setPostcode(vo.getPostCode());
        tbBusiness.setAddress1(vo.getBusinessAddress());
        tbBusiness.setAddress2(vo.getCountry());
        tbBusiness.setAddress3(vo.getCity());

        tbBusiness.setMemberSymbolgroup(vo.getMosg());
        tbBusiness.setBusinessDepotSize(vo.getDepotSize());
        tbBusiness.setIou("Not allowed ");
        tbBusiness.setIoulimit(0);
        tbBusiness.setPrimaryDepot(1);
        tbBusiness.setPriceScheme("Standard");
        tbBusiness.setRecordAt(LocalDateTime.now());
        tbBusiness.setCreateAt(LocalDateTime.now());
        tbBusiness.setUpdateAt(LocalDateTime.now());
        tbBusiness.setIsDelete(0);
        tbBusiness.setCompanyRegno(vo.getRegisterNo());
        String customer = "";
        for (String tag : vo.getCustomerTags()) {
            customer += ","+tag;
        }
        tbBusiness.setCustomerTags(customer.substring(1));
        businessMapper.insert(tbBusiness);
        if(!ArrayUtil.isEmpty(vo.getComment())){
            for (ApiAddBusinessVo.Comment s : vo.getComment()) {
                TbInteriorBusinessComment tbInteriorBusinessComment = new TbInteriorBusinessComment();
                tbInteriorBusinessComment.setAddTime(DataUtils.getDate());
                tbInteriorBusinessComment.setBusinessId(tbBusiness.getId());
                tbInteriorBusinessComment.setContent(s.getContent());
                tbInteriorBusinessComment.setManagerName("manager");
                tbInteriorBusinessComment.setCreateTime(LocalDateTime.now());
                tbInteriorBusinessComment.setUpdateTime(LocalDateTime.now());
                tbInteriorBusinessComment.setIsDelete(0);
                commentMapper.insert(tbInteriorBusinessComment);
            }
        }
        if(tbUser == null) {
            logger.info("该用户第一次注册");
            logger.info("添加tb_user");
            tbUser = new TbUser();
            BeanUtil.copyProperties(vo, tbUser);
            tbUser.setUpdateAt(LocalDateTime.now());
            tbUser.setCreateAt(LocalDateTime.now());
            tbUser.setIsDelete(0);
            tbUser.setRegisterStatus(0);
            tbUser.setMktPrefer(vo.getPreference());
            userMapper.insert(tbUser);
        }else {
            logger.info("该用户第二次注册");

        }
        logger.info("添加tb_business_user");
        TbBusinessUser businessUser = new TbBusinessUser();
        BeanUtil.copyProperties(vo,businessUser);
        businessUser.setUserId(tbUser.getId());
        businessUser.setBusinessId(tbBusiness.getId());
        businessUser.setCreateAt(LocalDateTime.now());
        businessUser.setUpdateAt(LocalDateTime.now());
        businessUser.setRelation(1);
        businessUser.setIsDelete(0);
        businessUserMapper.insert(businessUser);
        logger.info("请求stl获取customer");
        logger.info("添加tb_business_customer");
        TbBusinessCustomer businessCustomer = new TbBusinessCustomer();
        businessCustomer.setBusinessId(tbBusiness.getId());
        businessCustomer.setCid(tbBusinessCustomer.getCid()+1);
        businessCustomer.setStlCustomerId(tbBusinessCustomer.getStlCustomerId()+1);
        businessCustomer.setDepotId(1);
        businessCustomerMapper.insert(businessCustomer);
        logger.info("添加上传文件");
        if(!ArrayUtil.isEmpty(vo.getFiles())){
            for (ApiAddBusinessVo.Files file : vo.getFiles()) {
                TbBusinessDocuments businessDocuments = new TbBusinessDocuments();
                businessDocuments.setCreateAt(LocalDateTime.now());
                businessDocuments.setFileName(file.getFileUrl().split("/")[file.getFileUrl().split("/").length-1]);
                businessDocuments.setMimeType(businessDocuments.getFileName().split("\\.")[1]);
                businessDocuments.setCreateBy(1);
                businessDocuments.setUpdateBy(1);
                businessDocuments.setUpdateAt(LocalDateTime.now());
                businessDocuments.setBusinessId(tbBusiness.getId());
                businessDocuments.setCategory(file.getFileType());
                businessDocumentsMapper.insert(businessDocuments);
            }

        }

        return ResponseResult.success("注册成功");    }

    @Override
    public ResponseResult findByBusinessId(Integer businessId) {
        TbBusiness tbBusiness = businessMapper.selectOne(new QueryWrapper<TbBusiness>().lambda()
                .eq(TbBusiness::getId, businessId)
                .eq(TbBusiness::getIsDelete, 0));
        if(tbBusiness == null){
            throw new CustomException(Status.DATA_ERROR.code,Status.DATA_ERROR.message);
        }
        ApiFindByBusinessIdRequestVo vo = new ApiFindByBusinessIdRequestVo();
        BeanUtil.copyProperties(tbBusiness,vo);
        vo.setBusinessId(tbBusiness.getId());
        vo.setBusinessAddress(tbBusiness.getAddress1());
        vo.setCountry(tbBusiness.getAddress2());
        vo.setCity(tbBusiness.getAddress3());
        vo.setMosg(tbBusiness.getMemberSymbolgroup());
        vo.setPostCode(tbBusiness.getPostcode());
        vo.setDepotSize(tbBusiness.getBusinessDepotSize());
        vo.setRegisterNo(tbBusiness.getCompanyRegno());
        vo.setDepotId(tbBusiness.getPrimaryDepot());
        //user
        TbUser tbUser = userMapper.selectOne(new QueryWrapper<TbUser>().lambda().eq(TbUser::getId, businessUserMapper.selectOne(new QueryWrapper<TbBusinessUser>().lambda()
                .eq(TbBusinessUser::getBusinessId, tbBusiness.getId())
                .eq(TbBusinessUser::getRelation, 1).eq(TbBusinessUser::getIsDelete, 0)).getUserId()));
        BeanUtil.copyProperties(tbUser,vo);
        vo.setPreference(tbUser.getMktPrefer());
        //customerTags
        if(!StringUtils.isEmpty(tbBusiness.getCustomerTags())){
            try {
                String[] split = tbBusiness.getCustomerTags().split(",");
                vo.setCustomerTags(split);
            }catch (Exception e){
                logger.error("CustomerTags数组无法解析");
                e.printStackTrace();

            }
        }

        //files
        List<ApiAddRequestFileVo> byBusinessId = businessDocumentsMapper.findByBusinessId(businessId);
        if(!CollectionUtil.isEmpty(byBusinessId)){
            logger.info("封装路径");
            byBusinessId.forEach(a->{
                a.setFileUrl("http://"+ip+":8080/images/file/"+a.getFileName());
                a.setAddTime(DataUtils.getDate(a.getAddTime()));
            });
        }
        vo.setFiles(byBusinessId);
        //备注
        List<TbInteriorBusinessComment> tbInteriorBusinessComments = commentMapper.selectList(new QueryWrapper<TbInteriorBusinessComment>().lambda()
                .eq(TbInteriorBusinessComment::getBusinessId, businessId));
        List<ApiCommentRequestVo> commentRequestVos = new ArrayList<>();

        if(!CollectionUtil.isEmpty(tbInteriorBusinessComments)){
            tbInteriorBusinessComments.forEach(a->{
                ApiCommentRequestVo apiCommentRequestVo = new ApiCommentRequestVo();
                BeanUtil.copyProperties(a,apiCommentRequestVo);
                commentRequestVos.add(apiCommentRequestVo);
            });
        }

        List<ApiFindByBusinessIdRequestVo.ApiBusinessCustomer> customers = businessMapper.selectCustomerList(businessId);
        vo.setCustomers(customers);

        vo.setComment(commentRequestVos);
        return ResponseResult.success(vo);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult updateBusiness(ApiUpdateBusinessVo vo) {
        //查询business
        TbBusiness tbBusiness = businessMapper.selectOne(new QueryWrapper<TbBusiness>().lambda()
                .eq(TbBusiness::getId, vo.getBusinessId())
                .eq(TbBusiness::getIsDelete, 0));
        if(tbBusiness == null){
            throw new CustomException(Status.DATA_ERROR.code,Status.DATA_ERROR.message);
        }
        BeanUtil.copyProperties(vo,tbBusiness);
        tbBusiness.setPostcode(vo.getPostCode());
        tbBusiness.setAddress1(vo.getBusinessAddress());
        tbBusiness.setAddress2(vo.getCountry());
        tbBusiness.setAddress3(vo.getCity());

        tbBusiness.setMemberSymbolgroup(vo.getMosg());
        tbBusiness.setBusinessDepotSize(vo.getDepotSize());
        tbBusiness.setCompanyRegno(vo.getRegisterNo());
        tbBusiness.setUpdateAt(LocalDateTime.now());
        String customer = "";
        for (String tag : vo.getCustomerTags()) {
            customer += ","+tag;
        }
        tbBusiness.setCustomerTags(customer.substring(1));
        tbBusiness.setCompanyRegno(vo.getRegisterNo());
        //更新business数据
        businessMapper.updateById(tbBusiness);

        //查询business_user
        TbBusinessUser businessUser = businessUserMapper.selectOne(new QueryWrapper<TbBusinessUser>().lambda()
                .eq(TbBusinessUser::getBusinessId, tbBusiness.getId())
                .eq(TbBusinessUser::getRelation, 1).eq(TbBusinessUser::getIsDelete, 0));
        businessUser.setFirstName(vo.getFirstName());
        businessUser.setLastName(vo.getLastName());
        businessUser.setMobile(vo.getMobile());
        businessUser.setUpdateAt(LocalDateTime.now());
        //更新business_user
        businessUserMapper.updateById(businessUser);

        //查询user
        TbUser tbUser = userMapper.selectOne(new QueryWrapper<TbUser>().lambda().eq(TbUser::getId, businessUser.getUserId()));
        tbUser.setMobile(vo.getMobile());
        tbUser.setLastName(vo.getLastName());
        tbUser.setFirstName(vo.getFirstName());
        tbUser.setEmail(vo.getEmail());
        tbUser.setMktPrefer(vo.getPreference());
        //更新user
        userMapper.updateById(tbUser);
        //更新files
        if(!ArrayUtil.isEmpty(vo.getFiles())){
            for (ApiUpdateBusinessVo.Files file : vo.getFiles()) {
                if(file.getId() == null) {
                    TbBusinessDocuments businessDocuments = new TbBusinessDocuments();
                    businessDocuments.setCreateAt(LocalDateTime.now());
                    businessDocuments.setFileName(file.getFileUrl().split("/")[file.getFileUrl().split("/").length - 1]);
                    businessDocuments.setMimeType(businessDocuments.getFileName().split("\\.")[1]);
                    businessDocuments.setCreateBy(1);
                    businessDocuments.setUpdateBy(1);
                    businessDocuments.setUpdateAt(LocalDateTime.now());
                    businessDocuments.setBusinessId(tbBusiness.getId());
                    businessDocuments.setCategory(file.getFileType());
                    businessDocumentsMapper.insert(businessDocuments);
                }
            }

        }

        return ResponseResult.success("successfully");    }
}
