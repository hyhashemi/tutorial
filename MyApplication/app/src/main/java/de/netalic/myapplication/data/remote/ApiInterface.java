package de.netalic.myapplication.data.remote;

import org.json.JSONObject;

import java.util.List;

import de.netalic.myapplication.data.model.Speciality;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;

public interface ApiInterface {

    @GET("apiv1/specialities")
    Call<List<Speciality>> get();

    @FormUrlEncoded
    @HTTP(method = "CLAIM", path = "apiv1/phones", hasBody = true)
    Call<JSONObject> claim(@Field("udid") String udid, @Field("phone") String phone);

    @FormUrlEncoded
    @HTTP(method = "BIND", path = "phones", hasBody = true)
    Call<JSONObject> bind(@Field("udid") String udid, @Field("phone") String phone, @Field("deviceName") String deviceName, @Field("activationCode") String activationCode);

}
