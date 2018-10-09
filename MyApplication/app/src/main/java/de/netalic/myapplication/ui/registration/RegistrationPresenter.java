package de.netalic.myapplication.ui.registration;

import android.util.Log;

import org.json.JSONObject;

import de.netalic.myapplication.data.remote.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    public RegistrationFragment mRegistrationFragment;
    private ApiInterface apiInterface;


    public RegistrationPresenter(RegistrationFragment registrationFragment) {
        this.mRegistrationFragment = registrationFragment;
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://nightly.alpha.carrene.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        apiInterface = retrofit.create(ApiInterface.class);
    }


    public void claimRequest(final String phoneNumber){

        apiInterface.claim("2b6f0cc904d137be2e1730235f5664094b831186", phoneNumber).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                Log.e("status", String.valueOf(response.code()));
                if (response.code() == 200){
                    mRegistrationFragment.navigateToPhoneConfirm();
                }else{
                    mRegistrationFragment.snackbarError();
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                mRegistrationFragment.snackbarError();
            }
        });

    }
}
