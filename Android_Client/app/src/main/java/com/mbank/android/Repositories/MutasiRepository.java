package com.mbank.android.Repositories;

import androidx.lifecycle.MutableLiveData;

import com.mbank.android.model.MutasiRequest;
import com.mbank.android.model.APIResponse;
import com.mbank.android.networking.API;
import com.mbank.android.networking.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MutasiRepository {
    private static MutasiRepository mutasiRepository;
    private API api;

    public static MutasiRepository getInstance(){
        if (mutasiRepository == null){
            mutasiRepository = new MutasiRepository();
        }
        return mutasiRepository;
    }

    public MutasiRepository(){
        api = RetrofitService.createService(API.class);
    }

    public MutableLiveData<APIResponse> getMutasi(String token, MutasiRequest mutasiRequest){
        MutableLiveData<APIResponse> saldoResult = new MutableLiveData<>();
        api.getMutasi(token, mutasiRequest.getStartDate(), mutasiRequest.getEndDate()).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                saldoResult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                System.out.println("Test : masuk");
                saldoResult.setValue(null);
            }

        });
        return saldoResult;
    }
}
