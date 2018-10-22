package de.netalic.myapplication.ui.phoneconfirm;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.JsonObject;

import de.netalic.myapplication.data.remote.ApiClient;
import de.netalic.myapplication.data.remote.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneConfirmPresenter implements PhoneConfirmContract.Presenter {

    private ApiInterface mApiInterface;
    private PhoneConfirmFragment mPhoneConfirmFragment;
    private String mPhoneNumber;

    private String mToken;

    public PhoneConfirmPresenter(PhoneConfirmFragment phoneConfirmFragment){
        this.mPhoneConfirmFragment = phoneConfirmFragment;
    }

    public void bindRequest(String activationCode, final String phoneNumber){
        mApiInterface = ApiClient.getApiInterface("http://nightly.alpha.carrene.com/");
        mApiInterface.bind("2b6f0cc904d137be2e1730235f5664094b831186", phoneNumber, "SM-12345678", activationCode).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200){
                    JsonObject jsonObject = response.body();
                    mToken = jsonObject.get("token").getAsString();
                    mPhoneNumber = phoneNumber;
                    saveToken();
                    mPhoneConfirmFragment.navigateToShow();
                }
                else{
                    mPhoneConfirmFragment.snackbarError();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });


    }

    private void saveToken() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mPhoneConfirmFragment.getContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("token", mToken);
        editor.putString("phone", mPhoneNumber);
        editor.apply();
    }
}
