package de.netalic.myapplication.ui.registration;

import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import de.netalic.myapplication.data.remote.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    public RegistrationFragment mRegistrationFragment;
    private String mToken;
    private String phoneNumber;
    private ApiInterface apiInterface;
    private String activationCode;

    public RegistrationPresenter(RegistrationFragment registrationFragment) {
        this.mRegistrationFragment = registrationFragment;
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://nightly.alpha.carrene.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public void bindRequest(String activationCode){
        apiInterface.bind(phoneNumber,"123456789", mRegistrationFragment.mPhoneNumber, "").enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                JSONObject jsonObject = response.body();
                try {
                    mToken = jsonObject.getString("token");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mRegistrationFragment.getToken(mToken);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

            }
        });

    }

    public void claimRequest(final String phoneNumber){
        this.phoneNumber = phoneNumber;

        apiInterface.claim("2b6f0cc904d137be2e1730235f5664094b831186", phoneNumber).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                Log.e("status", String.valueOf(response.code()) );
                mRegistrationFragment.navigateToPhoneConfirm();
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Log.e("send", "onFail: " );
            }
        });

    }
}
