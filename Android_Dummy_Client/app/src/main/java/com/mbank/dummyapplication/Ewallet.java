package com.mbank.dummyapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.mbank.dummyapplication.databinding.ActivityEwalletBinding;
import com.mbank.dummyapplication.model.VirtualAccount;
import com.mbank.dummyapplication.viewmodel.VaViewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Ewallet extends AppCompatActivity {

    ActivityEwalletBinding binding;

    VaViewModel vaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEwalletBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
    }

    void init(){
        vaViewModel = ViewModelProviders.of(this).get(VaViewModel.class);
        vaViewModel.init();
        binding.reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reload();
            }
        });
    }

    void reload(){
        String va = binding.vaEditText.getText().toString();
        vaViewModel.getSaldo(va).observe(this, new Observer<VirtualAccount>() {
            @Override
            public void onChanged(VirtualAccount virtualAccount) {
                binding.vaTextView.setText(virtualAccount.getVirtualAccount());
                binding.namaTextView.setText(virtualAccount.getNama());

                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                formatRp.setCurrencySymbol("Rp. ");
                formatRp.setMonetaryDecimalSeparator(',');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);
                binding.saldoTextView.setText(kursIndonesia.format(virtualAccount.getSaldo()));
            }
        });
    }

}