package com.example.classproject3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ServiceHandler {

    String response = null;
    String url = null;

    public ServiceHandler(String url ){
        this.url = url ;
    }

    public String makeServiceCall(){
        BufferedReader br = null;
        URLConnection urlConnection;

        try {
            URL urlObject = new URL(url);
            urlConnection = urlObject.openConnection();
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;

            while((line = br.readLine()) != null){
                sb.append(line);
            }


            response = sb.toString();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return response;


    }
}

