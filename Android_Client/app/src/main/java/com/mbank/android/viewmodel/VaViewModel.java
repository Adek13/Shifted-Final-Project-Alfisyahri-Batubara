package com.mbank.android.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mbank.android.Repositories.VaRepository;
import com.mbank.android.model.APIResponse;
import com.mbank.android.model.VaRequest;

public class VaViewModel extends ViewModel {

    private VaRepository vaRepository;

    private LiveData<APIResponse> mutableLiveData;

    public void init(){
        if (mutableLiveData!= null){
            return;
        }
        vaRepository = VaRepository.getInstance();
    }

    public LiveData<APIResponse> getAccount(String token, VaRequest vaRequest){
        if (mutableLiveData == null){
            vaRepository = VaRepository.getInstance();
        }
        mutableLiveData = vaRepository.getAccount(token, vaRequest);

        return mutableLiveData;
    }

    public LiveData<APIResponse> topUp(String token, VaRequest vaRequest){
        System.out.println("Test1 : " + vaRequest.getVirtualAccount());
        if (mutableLiveData == null){
            vaRepository = VaRepository.getInstance();
        }
        mutableLiveData = vaRepository.topUp(token, vaRequest);

        return mutableLiveData;
    }

}
