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
import com.mbank.android.databinding.ActivityConfirmationBinding;
import com.mbank.android.model.APIResponse;
import com.mbank.android.model.VaRequest;
import com.mbank.android.viewmodel.VaViewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ConfirmationActivity extends AppCompatActivity {

    ActivityConfirmationBinding binding;

    private Double nominal;

    VaViewModel vaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        onClickGroup();
    }

    void init(){
        Bundle bundle = getIntent().getExtras();
        binding.nomorVaTextView.setText(bundle.getString("virtualAccount", "Error!"));
        this.nominal = bundle.getDouble("nominal", 0);

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);

        binding.nominalTv.setText(kursIndonesia.format(nominal));
        binding.totalTextView.setText(kursIndonesia.format(nominal + 2000));
        vaViewModel = ViewModelProviders.of(this).get(VaViewModel.class);
        vaViewModel.init();
    }

    void onClickGroup(){
        binding.topUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("com.mbank.android.logintoken", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("com.mbank.android.logintoken", "");
                VaRequest vaRequest = new VaRequest(binding.nomorVaTextView.getText().toString(), "topUp", nominal);
                doTopUp(token , vaRequest);
            }
        });
    }

    void doTopUp(String token, VaRequest vaRequest){
        vaViewModel.topUp(token, vaRequest).observe(this, new Observer<APIResponse>() {
            @Override
            public void onChanged(APIResponse apiResponse) {
                Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                Bundle bundle = new Bundle();
                if (apiResponse.getCode()==200){
                    bundle.putString("redirect", "dashboard");
                    bundle.putInt("code", 200);
                    bundle.putString("message", "Top Up Berhasil!");
                }else{
                    bundle.putString("redirect", "dashboard");
                    bundle.putInt("code", 404);
                    bundle.putString("message", "Top Up Gagal!");
                }
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}