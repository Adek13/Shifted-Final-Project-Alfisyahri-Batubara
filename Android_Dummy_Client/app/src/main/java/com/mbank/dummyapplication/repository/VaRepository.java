package com.mbank.dummyapplication.repository;

import androidx.lifecycle.MutableLiveData;

import com.mbank.dummyapplication.model.VirtualAccount;
import com.mbank.dummyapplication.networking.API;
import com.mbank.dummyapplication.networking.RetrofitService;

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

    public MutableLiveData<VirtualAccount> getAccount(String va){
        MutableLiveData<VirtualAccount> accountResult = new MutableLiveData<>();
        api.getAccount(va).enqueue(new Callback<VirtualAccount>() {
            @Override
            public void onResponse(Call<VirtualAccount> call, Response<VirtualAccount> response) {
                System.out.println("masuk!");
                accountResult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<VirtualAccount> call, Throwable t) {
                accountResult.setValue(null);
            }
        });
        return accountResult;
    }

}
