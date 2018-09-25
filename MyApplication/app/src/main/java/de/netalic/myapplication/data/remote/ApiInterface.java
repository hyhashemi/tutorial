package de.netalic.myapplication.data.remote;

import java.util.List;

import de.netalic.myapplication.data.model.Model;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("apiv1/specialities")
    Call<List<Model>> get();
}
