package de.netalic.myapplication.data.remote;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import de.netalic.myapplication.MyApp;
import de.netalic.myapplication.utils.SharedPreference;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiInterface mApiInterface;
    public static Retrofit.Builder mBuilder;

    public static ApiInterface getApiInterface(String url){

        OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
        okHttpClient.addInterceptor(new CustomInterceptor());

        mBuilder = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = mBuilder.build();
        mApiInterface = retrofit.create(ApiInterface.class);
        return mApiInterface;
    }

    public static class CustomInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            SharedPreference sharedPref = new SharedPreference(MyApp.getApp().getApplicationContext());
            String token = sharedPref.getToken();
            if (token != null && request.url().toString().contains("wallets")){
                request = request.newBuilder().addHeader("Authorization", token).build();
            }

            Response response = chain.proceed(request);

            if (request.method().equals("BIND") && response.code() == 200 ){
                String responseBodyString = response.body().string();
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = (JsonObject) jsonParser.parse(responseBodyString);
                String newToken = jsonObject.get("token").getAsString();
                sharedPref.setToken(newToken);
                MediaType contentType = response.body().contentType();
                ResponseBody body = ResponseBody.create(contentType, responseBodyString);
                response = response.newBuilder().body(body).build();
            }

            return response;
        }
    }

}
