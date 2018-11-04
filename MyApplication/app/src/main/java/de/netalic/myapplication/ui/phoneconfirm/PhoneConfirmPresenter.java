package de.netalic.myapplication.ui.phoneconfirm;

import com.google.gson.JsonObject;

import de.netalic.myapplication.data.remote.ApiClient;
import de.netalic.myapplication.data.remote.ApiInterface;
import de.netalic.myapplication.utils.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneConfirmPresenter implements PhoneConfirmContract.Presenter {

    private ApiInterface mApiInterface;
    private PhoneConfirmFragment mPhoneConfirmFragment;
    private String mPhoneNumber;

    public PhoneConfirmPresenter(PhoneConfirmFragment phoneConfirmFragment){
        this.mPhoneConfirmFragment = phoneConfirmFragment;
    }

    public void bindRequest(String activationCode, final String phoneNumber, String udId, String deviceName){
        mApiInterface = ApiClient.getApiInterface("http://nightly.alpha.carrene.com/");
        mApiInterface.bind(udId, phoneNumber, deviceName, activationCode).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200){
                    mPhoneNumber = phoneNumber;
                    SharedPreference sharedPref = new SharedPreference(mPhoneConfirmFragment.getActivity().getApplicationContext());
                    sharedPref.setPhoneNumber(mPhoneNumber);
                    mPhoneConfirmFragment.navigateToShow();
                }
                else{
                    mPhoneConfirmFragment.snackbarError();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                mPhoneConfirmFragment.snackbarError();
            }
        });
    }

}
