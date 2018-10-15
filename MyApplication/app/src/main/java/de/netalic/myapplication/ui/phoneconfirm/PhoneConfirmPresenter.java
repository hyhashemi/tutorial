package de.netalic.myapplication.ui.phoneconfirm;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
        mApiInterface.bind("2b6f0cc904d137be2e1730235f5664094b831186", phoneNumber, "SM-12345678", activationCode).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200){
                    JsonObject jsonObject = response.body();
                    mToken = jsonObject.get("token").getAsString();
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
        editor.apply();
    }
}
