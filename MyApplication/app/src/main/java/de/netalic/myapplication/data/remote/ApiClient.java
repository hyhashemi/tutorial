package de.netalic.myapplication.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiInterface mApiInterface;


    public static ApiInterface getApiInterface(String url){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        mApiInterface = retrofit.create(ApiInterface.class);
        return mApiInterface;
    }

}
