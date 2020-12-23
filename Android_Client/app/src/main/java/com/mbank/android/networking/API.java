package com.mbank.android.networking;

import com.mbank.android.model.LoginRequest;
import com.mbank.android.model.MutasiRequest;
import com.mbank.android.model.MutasiResponse;
import com.mbank.android.model.RegisterRequest;
import com.mbank.android.model.APIResponse;
import com.mbank.android.model.SaldoResponse;
import com.mbank.android.model.VaRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @POST("/register")
    Call<APIResponse> register (@Body RegisterRequest registerRequest);

    @POST("/login")
    Call<APIResponse> login (@Body LoginRequest loginRequest);

    @GET("/nasabah")
    Call<APIResponse> getNasabah (@Header("Authorization") String token);

    @GET("/saldo")
    Call<SaldoResponse> getSaldo (@Header("Authorization") String token);

    @GET("/account/{va}")
    Call<APIResponse> getAccount (@Header("Authorization") String token,
                                  @Path("va") String va);

    @POST("/topup")
    Call<APIResponse> topUp (@Header("Authorization") String token, @Body VaRequest vaRequest);

    @GET("/mutasi")
    Call<APIResponse> getMutasi (@Header("Authorization") String token,
                                 @Query("startDate") String startDate,
                                 @Query("endDate") String endDate);
}
