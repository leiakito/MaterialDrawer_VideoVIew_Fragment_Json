package com.leia.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
    Author:leia
    Write The Code Change The World    
*/public class GetHttp {


    /**
     * 网络请求 ,将流转为 字符串 并且返回
     */

    public static String getJson() throws IOException {
        //创建url对象
        URL url = new URL("https://www.suika.fun/json/result.json");
        //HTTPUIRLConnection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        //开启connection链接
        connection.connect();
        //返回code
        int code = connection.getResponseCode();
        if (code == HttpURLConnection.HTTP_OK) {
            //获取返回的流
            InputStream inputStream = connection.getInputStream();
            //将流转换为字符串
            StringBuffer sb = new StringBuffer();
            //            byte[] byt=new byte[8192];
//
//            for (int i;(i=inputStream.read(byt))!=-1;){
//                sb.append(new String(byt),0,i);
//            }
            String line;

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                sb.append(line);

            }
            String reuslt = sb.toString();
            Log.d("msg", "GetHTTP" + reuslt);

            return reuslt;
        }
        return null;
    }
}
