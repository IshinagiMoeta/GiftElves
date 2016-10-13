package com.bystanders.moeta.giftelves.download;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ishinagi_moeta on 2016/9/26.
 */
public interface ByteService {
    @GET("/myWeb/MyServlet")
    Call<InputStream> getByte(@Query("byte") InputStream in);
}
