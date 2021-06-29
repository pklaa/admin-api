package com.dmc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {
 
    /**
     * 获取当前时间
     * @param format 格式 例如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentDate(String format){
        SimpleDateFormat s = new SimpleDateFormat(format);
        return s.format(new Date());
    }

    public static String getYesterday(String format){
        SimpleDateFormat s = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.HOUR, -24);
        return s.format(c.getTime());
    }

    public static String getDate(){

            //括号内制定格式化时间格式
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            // 格式化时间 2019-09-30
        Date date = new Date();// 输出结果 Mon Sep 30 00:00:00 CST 2019
            return date.toString();


    }
    public static String getDate(String time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date newTime = format.parse(time);
            return newTime.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


 
    /**
     * 获取上一个月
     * @param format 格式 例如：yyyy-MM
     */
    public static String getPreDate(String format){
        SimpleDateFormat ss = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        return ss.format(c.getTime());
    }
 
    public static void main (String[] args){

        String time = "2020-02-02 02:02:02";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date newTime = format.parse(time);
            System.out.println("转换以后的时间：："+newTime);//Sun Feb 02 02:02:02 CST 2020
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}