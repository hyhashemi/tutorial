package de.netalic.myapplication.ui.enter;

import java.util.List;

import de.netalic.myapplication.data.model.Speciality;
import de.netalic.myapplication.data.model.Wallet;
import de.netalic.myapplication.data.remote.ApiInterface;
import de.netalic.myapplication.data.remote.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterPresenter implements EnterContract.Presenter{

    private EnterContract.View mEnterFragment;
    private ApiInterface mApiInterfaceSpeciality;
    private ApiInterface mApiInterfaceWallet;
    private String mToken;

    public EnterPresenter(EnterFragment enterFragment){
        this.mEnterFragment = enterFragment;
    }

    @Override
    public void showRequest(){
        mApiInterfaceSpeciality = ApiClient.getApiInterface("https://nightly-nc.carrene.com/");
        mApiInterfaceSpeciality.get().enqueue(new Callback<List<Speciality>>() {
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
        mApiInterfaceWallet = ApiClient.getApiInterface("http://nightly.alpha.carrene.com/");
        mApiInterfaceWallet.listWallets(mToken).enqueue(new Callback<List<Wallet>>() {
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
