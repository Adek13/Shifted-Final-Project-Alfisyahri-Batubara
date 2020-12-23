package com.mbank.android.Repositories;

import androidx.lifecycle.MutableLiveData;

import com.mbank.android.model.LoginRequest;
import com.mbank.android.model.RegisterRequest;
import com.mbank.android.model.APIResponse;
import com.mbank.android.model.APIResponse;
import com.mbank.android.networking.API;
import com.mbank.android.networking.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private static UserRepository userRepository;
    private API api;

    public static UserRepository getInstance(){
        if (userRepository == null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public UserRepository(){
        api = RetrofitService.createService(API.class);
    }

    public MutableLiveData<APIResponse> postRegister(RegisterRequest registerRequest){
        MutableLiveData<APIResponse> response = new MutableLiveData<>();
        api.register(registerRequest).enqueue(new Callback<APIResponse>() {
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

    public MutableLiveData<APIResponse> postLogin(LoginRequest loginRequest){
        MutableLiveData<APIResponse> loginResult = new MutableLiveData<>();
        api.login(loginRequest).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                loginResult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                loginResult.setValue(null);
            }
        });
        return loginResult;
    }

    public MutableLiveData<APIResponse> getNasabah(String token){
        MutableLiveData<APIResponse> nasabahResult = new MutableLiveData<>();
        api.getNasabah(token).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                nasabahResult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                nasabahResult.setValue(null);
            }
        });
        return nasabahResult;
    }

}
