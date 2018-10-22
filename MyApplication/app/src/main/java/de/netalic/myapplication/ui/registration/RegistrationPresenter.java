package de.netalic.myapplication.ui.registration;

import android.util.Log;

import org.json.JSONObject;

import de.netalic.myapplication.data.remote.ApiInterface;
import de.netalic.myapplication.data.remote.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    public RegistrationFragment mRegistrationFragment;
    private ApiInterface apiInterface;


    public RegistrationPresenter(RegistrationFragment registrationFragment) {
        this.mRegistrationFragment = registrationFragment;

    }


    public void claimRequest(final String phoneNumber){

        apiInterface = ApiClient.getApiInterface("http://nightly.alpha.carrene.com/");
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
