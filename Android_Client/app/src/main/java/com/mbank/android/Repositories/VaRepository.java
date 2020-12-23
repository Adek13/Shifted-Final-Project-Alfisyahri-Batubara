package com.mbank.android.Repositories;

import androidx.lifecycle.MutableLiveData;

import com.mbank.android.model.APIResponse;
import com.mbank.android.model.VaRequest;
import com.mbank.android.networking.API;
import com.mbank.android.networking.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaRepository {

    private static VaRepository vaRepository;
    private API api;

    public static VaRepository getInstance(){
        if (vaRepository == null){
            vaRepository = new VaRepository();
        }
        return vaRepository;
    }

    public VaRepository(){
        api = RetrofitService.createService(API.class);
    }

    public MutableLiveData<APIResponse> getAccount(String token, VaRequest vaRequest){
        MutableLiveData<APIResponse> response = new MutableLiveData<>();
        api.getAccount(token, vaRequest.getVirtualAccount()).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> responses) {
                response.setValue(responses.body());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                response.setValue(null);
            }
        });
        return response;
    }

    public MutableLiveData<APIResponse> topUp(String token, VaRequest vaRequest){
        System.out.println("Test jumlah : " + vaRequest.getJumlah());
        MutableLiveData<APIResponse> response = new MutableLiveData<>();
        api.topUp(token, vaRequest).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> responses) {
                response.setValue(responses.body());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                response.setValue(null);
            }
        });
        return response;
    }

}
