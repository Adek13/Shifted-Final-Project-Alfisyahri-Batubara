package com.mbank.android.Repositories;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.mbank.android.model.Mutasi;
import com.mbank.android.model.SaldoResponse;
import com.mbank.android.model.RegisterRequest;
import com.mbank.android.networking.API;
import com.mbank.android.networking.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RekeningRepository {

    private static RekeningRepository rekeningRepository;
    private API api;

    public static RekeningRepository getInstance(){
        if (rekeningRepository == null){
            rekeningRepository = new RekeningRepository();
        }
        return rekeningRepository;
    }

    public RekeningRepository(){
        api = RetrofitService.createService(API.class);
    }

    public MutableLiveData<SaldoResponse> getSaldo(String token){
        MutableLiveData<SaldoResponse> saldoResult = new MutableLiveData<>();
        api.getSaldo(token).enqueue(new Callback<SaldoResponse>() {
            @Override
            public void onResponse(Call<SaldoResponse> call, Response<SaldoResponse> response) {
                saldoResult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SaldoResponse> call, Throwable t) {
                saldoResult.setValue(null);
            }
        });
        return saldoResult;
    }

}
