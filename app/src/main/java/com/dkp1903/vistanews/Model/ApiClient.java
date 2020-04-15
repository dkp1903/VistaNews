package com.dkp1903.vistanews.Model;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
* Retrofit is a REST Client for Java and Android.
* It makes it relatively easy to retrieve and upload JSON (or other structured data)
* via a REST based webservice.
* In Retrofit you configure which converter is used for the data serialization.
* */

public class ApiClient {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static ApiClient apiClient;
    private static Retrofit retrofit;

    private ApiClient () {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static synchronized ApiClient getInstance(){
        if (apiClient == null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public ApiInterface getApi() {
        return retrofit.create(ApiInterface.class);
    }
}
