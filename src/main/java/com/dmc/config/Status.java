package com.dmc.config;

/**
 * 枚举类对象
 */
public enum Status {

    // 公共
    SUCCESS(200, "succeed"),

    //注册
    PARAMETER_ERROR(1001, "Missing parameter"),
    PHONE_NOT_EXIST(1002,"User's mobile phone number does not exist, please register information first"),
    LOGIN_USER_EXIST(1003, "This user has registered APP, please log in"),
    CODE_EXPIRED(1004,"Verification code invalidation"),
    CODE_ERROR(1005,"Verification code error"),
    PASSWORD_ERROR(1006,"Password Inconsistency"),
    PHONE_ERROR(1007,"The phone number is illegal"),
    EMAIL_ERROR(1008,"The email is not legal."),
    MESSAGE_ERROR(1009,"Incorrect input, please check and try again"),
    // 登录
    LOGIN_ERROR(2001, "wrong password "),
    LOGIN_USER_NOT_EXIST(2002, "The user does not exist, please register information first"),
    LOGIN_USER_NOT_APP(2003, "This user is not registered in APP, please go to register"),
    TOKEN_NULL(2004,"TOKEN is empty"),
    TOKEN_ERROR(2005,"TOKEN is not valid"),
    TOKEN_PAST(2006,"TOKEN failure"),
    USER_DATA_ERROR(2007,"User data not found"),
    DEVICEID_ERROR(2014,"You are logging into a new device"),
    //账户
    ACCOUNT_ERROR(2008,"You have not added any accounts"),
    ACCOUNT_EXIST(2009,"You have already added this account"),
    ACCOUNT_NOT_EXIST(2010,"This account has been deleted, please do not repeat the operation"),
    BUSINESSID_ERROR(2011,"BusinessId does not exist"),
    ACCOUNT_NUMBER_ERROR(2012,"Please enter the correct 8-digit account number"),
    ACCOUNT_SORT_ERROR(2013,"Please enter the correct 6 digit SORT code"),
    //商品
    DATA_ERROR(3001,"Data is empty"),
    PRODUCT_NOT_EXIST(3002,"No such goods are available at the moment"),
    BAR_CODE_INVALID(3003,"No information on this item"),


    //个人中心
    CONTEXT_NULL(4000,"The input cannot be empty"),
    MESSAGE_INVALID(4001,"Please enter valid information,Input no less than 10 characters"),
    USER_NOT_JURISDICTION(4002,"The current user is not the shopkeeper"),
    CLERK_NULL(4003,"You haven't added any clerks yet"),
    CLERK_NOT_EXIST(4004,"The clerk does not exist"),
    USER_EXIST(4005,"The user already exists"),

    //API
    IMAGE_UPLOAD_FAILED(5001,"Image upload failed"),
    FILE_TYPE_ERROR(5002,"This file type is not supported"),
    STORE_REGISTERED(5003,"This store information has been registered"),
    INFORMATION_DELETED(5004,"Information deleted"),
    FILE_DELETE_ERROR(5005,"File deletion failed"),
    ;

    //bank

    public int code;
    public String message;

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
