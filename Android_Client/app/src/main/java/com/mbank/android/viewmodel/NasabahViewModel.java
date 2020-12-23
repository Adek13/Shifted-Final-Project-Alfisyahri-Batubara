package com.mbank.android.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mbank.android.Repositories.UserRepository;
import com.mbank.android.model.APIResponse;
import com.mbank.android.model.LoginRequest;

public class NasabahViewModel extends ViewModel {
    private UserRepository userRepository;

    private LiveData<APIResponse> mutableLiveData;

    public void init(){
        if (mutableLiveData!= null){
            return;
        }
        userRepository = UserRepository.getInstance();
    }
    public LiveData<APIResponse> getNasabah(String token){
        if (mutableLiveData == null){
            userRepository = UserRepository.getInstance();
        }
        mutableLiveData = userRepository.getNasabah(token);

        return mutableLiveData;
    }
}
