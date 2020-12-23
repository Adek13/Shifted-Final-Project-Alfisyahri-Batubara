package com.mbank.android.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mbank.android.Repositories.MutasiRepository;
import com.mbank.android.model.MutasiRequest;
import com.mbank.android.model.APIResponse;

public class MutasiViewModel extends ViewModel {

    private MutasiRepository mutasiRepository;

    private LiveData<APIResponse> mutableLiveData;

    public void init(){
        if (mutableLiveData!= null){
            return;
        }
        mutasiRepository = MutasiRepository.getInstance();
    }
    public LiveData<APIResponse> getMutasi(String token, MutasiRequest mutasiRequest){
        if (mutableLiveData == null){
            mutasiRepository = MutasiRepository.getInstance();
        }
        mutableLiveData = mutasiRepository.getMutasi(token, mutasiRequest);

        return mutableLiveData;
    }

}
