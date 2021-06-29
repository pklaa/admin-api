package com.dmc.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

    public static String httpPost(String url, Map<String,String> map){
        String str ="";
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
        // 创建Httpclient对象
         httpclient = HttpClients.createDefault();
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        // 设置2个post参数，一个是scope、一个是q
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        for (Map.Entry<String,String> m : map.entrySet()){
            parameters.add(new BasicNameValuePair(m.getKey(), m.getValue()));
        }

        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters,"UTF-8");
        // 将请求实体设置到httpPost对象中
        httpPost.setEntity(formEntity);
        // 执行请求
        response = httpclient.execute(httpPost);
        // 判断返回状态是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            str = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("response:"+str);
        }

        } catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return str;
    }
}
