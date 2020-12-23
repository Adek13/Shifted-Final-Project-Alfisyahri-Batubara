package com.mbank.dummyapplication.networking;

import com.mbank.dummyapplication.model.VirtualAccount;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {

    @GET("account/{id}")
    Call<VirtualAccount> getAccount (@Path("id") String virtualAccount);

}
