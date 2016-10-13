package com.bystanders.moeta.giftelves.utils;

import com.bystanders.moeta.giftelves.download.ByteService;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by Ishinagi_moeta on 2016/9/26.
 */
public class HttpUtils {
    public static final String BASE_URL = "http://www.1688wan.com";
    private static ByteService byteService;

    public static ByteService getByteService(){
        if(byteService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return null;
    }
}
