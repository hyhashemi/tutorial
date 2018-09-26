package de.netalic.myapplication.ui.registration;

import java.util.List;

import de.netalic.myapplication.data.model.Speciality;
import de.netalic.myapplication.data.remote.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationPresenter implements RegistrationContract.Presenter{

    private RegistrationContract.View mRegistrationFragment;

    public RegistrationPresenter(RegistrationFragment registrationFragment){
        this.mRegistrationFragment = registrationFragment;
    }

    @Override
    public void request() {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://nightly-nc.carrene.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        apiInterface.get().enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(Call<List<Speciality>> call, Response<List<Speciality>> response) {
                if (response.code() == 200) {
                  mRegistrationFragment.navigateToShowActivity(response.body());
                }
                else{
                    mRegistrationFragment.notFound();
                }
            }

            @Override
            public void onFailure(Call<List<Speciality>> call, Throwable t) {

            }
        });
    }
}
