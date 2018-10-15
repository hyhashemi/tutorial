package de.netalic.myapplication.ui.enter;

import android.util.Log;

import java.util.List;

import de.netalic.myapplication.data.model.Speciality;
import de.netalic.myapplication.data.model.Wallet;
import de.netalic.myapplication.data.remote.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnterPresenter implements EnterContract.Presenter{

    private EnterContract.View mEnterFragment;
    private ApiInterface mApiInterface;
    private ApiInterface mApiInterface2;
    private String mToken;

    public EnterPresenter(EnterFragment enterFragment){
        this.mEnterFragment = enterFragment;

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://nightly-nc.carrene.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit.Builder builder2 = new Retrofit.Builder()
                .baseUrl("http://nightly.alpha.carrene.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        mApiInterface = retrofit.create(ApiInterface.class);

        Retrofit retrofit2 = builder2.build();
        mApiInterface2 = retrofit2.create(ApiInterface.class);


    }

    @Override
    public void showRequest(){

        mApiInterface.get().enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(Call<List<Speciality>> call, Response<List<Speciality>> response) {
                if (response.code() == 200) {
                    mEnterFragment.navigateToShowActivity(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Speciality>> call, Throwable t) {
            }
        });
    }


    @Override
    public void walletRequest(String token) {
        this.mToken = token;
        mApiInterface2.listWallets(mToken).enqueue(new Callback<List<Wallet>>() {
            @Override
            public void onResponse(Call<List<Wallet>> call, Response<List<Wallet>> response) {
                if (response.code() == 200) {
                    mEnterFragment.navigateToWalletActivity(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Wallet>> call, Throwable t) {

            }
        });
    }


}
