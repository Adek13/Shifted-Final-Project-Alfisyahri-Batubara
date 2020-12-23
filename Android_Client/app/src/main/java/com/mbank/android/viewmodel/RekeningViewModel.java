package com.mbank.android.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mbank.android.Repositories.RekeningRepository;
import com.mbank.android.model.SaldoResponse;

public class RekeningViewModel extends ViewModel {

    private RekeningRepository rekeningRepository;

    private LiveData<SaldoResponse> mutableLiveData;

    public void init(){
        if (mutableLiveData!= null){
            return;
        }
        rekeningRepository = RekeningRepository.getInstance();
    }

    public LiveData<SaldoResponse> getSaldo(String token){
        if (mutableLiveData == null){
            rekeningRepository = RekeningRepository.getInstance();
        }
        mutableLiveData = rekeningRepository.getSaldo(token);

        return mutableLiveData;
    }

}
