package com.mbank.android.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mbank.android.databinding.ActivityTopUpBinding;
import com.mbank.android.model.APIResponse;
import com.mbank.android.model.VaRequest;
import com.mbank.android.viewmodel.VaViewModel;

public class TopUpActivity extends AppCompatActivity {

    ActivityTopUpBinding binding;

    VaViewModel vaViewModel;

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTopUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        onClickGroup();
    }

    void init(){
        SharedPreferences sharedPreferences = getSharedPreferences("com.mbank.android.logintoken", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("com.mbank.android.logintoken", "");
        vaViewModel = ViewModelProviders.of(this).get(VaViewModel.class);
        vaViewModel.init();
    }

    void onClickGroup(){
        binding.lanjutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double nominal = Double.parseDouble(binding.nominalEditText.getText().toString());
                if (nominal<20000){
                    showAlertNominal();
                }else{
                    String virtualAccount = binding.nomorEditText.getText().toString();
                    VaRequest vaRequest = new VaRequest(virtualAccount, "getVirtualAccount", new Double(0));
                    checkAccount(vaRequest);
                }
            }
        });
    }

    void checkAccount(VaRequest vaRequest){
        if (!token.equals("")){
            vaViewModel.getAccount(token, vaRequest).observe(this, new Observer<APIResponse>() {
                @Override
                public void onChanged(APIResponse apiResponse) {
                    if (apiResponse.getCode()!=200){
                        showAlert();
                    }else{
                        Intent intent = new Intent(getApplicationContext(), ConfirmationActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("virtualAccount", vaRequest.getVirtualAccount());
                        bundle.putDouble("nominal", Double.parseDouble(binding.nominalEditText.getText().toString()));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            });
        }else{
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    void showAlert(){
        new AlertDialog.Builder(this)
                .setTitle("Gagal!")
                .setMessage("Akun Tidak Ditemukan!")
                .setNegativeButton("Close", null)
                .show();
    }

    void showAlertNominal(){
        new AlertDialog.Builder(this)
                .setTitle("Alert!")
                .setMessage("Nominal Harus Lebih Besar Atau Sama dengan Rp. 20.000,00")
                .setNegativeButton("Close", null)
                .show();
    }

}