package com.mbank.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mbank.android.databinding.ActivityDashboardBinding;
import com.mbank.android.model.SaldoResponse;
import com.mbank.android.viewmodel.RekeningViewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DashboardActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;

    private RekeningViewModel rekeningViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        onClickGroup();
    }

    void init(){
        rekeningViewModel = ViewModelProviders.of(this).get(RekeningViewModel.class);
        rekeningViewModel.init();
        SharedPreferences sharedPreferences = getSharedPreferences("com.mbank.android.logintoken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("com.mbank.android.logintoken", "");
        rekeningViewModel.getSaldo(token).observe(this, new Observer<SaldoResponse>() {
            @Override
            public void onChanged(SaldoResponse saldoResponse) {

                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                formatRp.setCurrencySymbol("Rp. ");
                formatRp.setMonetaryDecimalSeparator(',');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);

                Double saldo = Double.parseDouble(saldoResponse.getPayload().toString());

                binding.nominalTextView.setText(kursIndonesia.format(saldo));
            }
        });
    }

    void onClickGroup(){
        binding.virtualAccountCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TopUpActivity.class);
                startActivity(intent);
            }
        });
        binding.mutasiCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MutasiActivity.class);
                startActivity(intent);
            }
        });
        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmation();
            }
        });
    }

    void showConfirmation(){
        new MaterialAlertDialogBuilder(this)
                .setMessage("Apakah Anda Yakin Akan Logout?")
                .setNegativeButton("Tidak", null)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences sharedPreferences = getSharedPreferences("com.mbank.android.logintoken", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("com.mbank.android.logintoken","");
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();

    }
}