package de.netalic.myapplication.ui.registration;

import java.util.List;

import de.netalic.myapplication.data.model.Model;
import de.netalic.myapplication.data.remote.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationPresenter implements Callback<List<Model>> {
    private RegistrationFragment mRegistrationFragment;

    public RegistrationPresenter(RegistrationFragment registrationFragment){
        this.mRegistrationFragment = registrationFragment;
    }

    @Override
    public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
        if (response.code() == 200) {
            mRegistrationFragment.navigateToShowActivity();
        }
        else{
            mRegistrationFragment.notFound();
        }
    }

    @Override
    public void onFailure(Call<List<Model>> call, Throwable t) {
    }

    public void request() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://nightly-nc.carrene.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<Model>> call = apiInterface.get();
        call.enqueue(this );
    }
}
