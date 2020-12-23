package com.mbank.android.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mbank.android.Repositories.UserRepository;
import com.mbank.android.model.LoginRequest;
import com.mbank.android.model.RegisterRequest;
import com.mbank.android.model.APIResponse;
import com.mbank.android.model.TokenResponse;

public class UserViewModel extends ViewModel {

    private UserRepository userRepository;

    private LiveData<APIResponse> mutableLiveData;

    public void init(){
        if (mutableLiveData!= null){
            return;
        }
        userRepository = UserRepository.getInstance();
    }

    public LiveData<APIResponse> postRegister(RegisterRequest registerRequest){
        if (mutableLiveData == null){
            userRepository = UserRepository.getInstance();
        }
        mutableLiveData = userRepository.postRegister(registerRequest);

        return mutableLiveData;
    }

    public LiveData<APIResponse> postLogin(LoginRequest loginRequest){
        if (mutableLiveData == null){
            userRepository = UserRepository.getInstance();
        }
        mutableLiveData = userRepository.postLogin(loginRequest);

        return mutableLiveData;
    }

}
