package de.netalic.myapplication.ui.phoneconfirm;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import de.netalic.myapplication.data.remote.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class PhoneConfirmPresenter implements PhoneConfirmContract.Presenter {

    private ApiInterface mApiInterface;
    private PhoneConfirmFragment mPhoneConfirmFragment;

    private String mToken;

    public PhoneConfirmPresenter(PhoneConfirmFragment phoneConfirmFragment){
        this.mPhoneConfirmFragment = phoneConfirmFragment;
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://nightly.alpha.carrene.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        mApiInterface = retrofit.create(ApiInterface.class);
    }

    public void bindRequest(String activationCode, final String phoneNumber){
        mApiInterface.bind("2b6f0cc904d137be2e1730235f5664094b831186", phoneNumber, "SM-12345678", activationCode).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                Log.e("bind request", String.valueOf(response.code()));
                JSONObject jsonObject = response.body();
                if (response.code() == 200){
                    try {
                        mToken = jsonObject.getString("token");
                    } catch (JSONException e) {
                         e.printStackTrace();
                    }
                    saveToken(mToken);
                    mPhoneConfirmFragment.navigateToShow();
                }
                else{
                    mPhoneConfirmFragment.snackbarError();
                }
            }
            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                mPhoneConfirmFragment.snackbarError();
            }
        });

    }

    private void saveToken(String token) {
        this.mToken = token;
        SharedPreferences.Editor editor = mPhoneConfirmFragment.getActivity().getSharedPreferences("token", MODE_PRIVATE).edit();
        editor.putString("token", mToken);
        editor.apply();
    }
}
