package com.algorithm.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpResponse implements HttpResponseInterface{

    HttpResponseInterface<String,String> httpResponseInterface;

    public String getResponse(String str){
        String response = "";
        try {
            URL url = new URL(str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            String content = "user_id="+ URLEncoder.encode("123", "gbk");
            outputStream.write(content.getBytes());
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] b = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int len = 0;
            while(true){
                len = inputStream.read(b);
                if(len == -1){
                    break;
                }
                byteArrayOutputStream.write(b, 0, len);
            }
            response = byteArrayOutputStream.toString();
            System.out.println(response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Object getResponseMsg(Object o) {
        return null;
    }
}
