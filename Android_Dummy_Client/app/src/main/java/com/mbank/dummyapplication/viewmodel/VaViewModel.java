package com.mbank.dummyapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mbank.dummyapplication.model.VirtualAccount;
import com.mbank.dummyapplication.repository.VaRepository;

public class VaViewModel extends ViewModel {

    private VaRepository vaRepository;

    private LiveData<VirtualAccount> mutableLiveData;

    public void init(){
        if (mutableLiveData!= null){
            return;
        }
        vaRepository = VaRepository.getInstance();
    }

    public LiveData<VirtualAccount> getSaldo(String va){
        if (mutableLiveData == null){
            vaRepository = VaRepository.getInstance();
        }
        mutableLiveData = vaRepository.getAccount(va);

        return mutableLiveData;
    }

}
