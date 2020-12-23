package com.mbank.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mbank.android.R;
import com.mbank.android.Repositories.UserRepository;
import com.mbank.android.model.APIResponse;
import com.mbank.android.model.Nasabah;
import com.mbank.android.viewmodel.NasabahViewModel;

public class MainActivity extends AppCompatActivity {

    private NasabahViewModel nasabahViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               init();
            }
        }, 3000);
    }

    void init(){
        nasabahViewModel = ViewModelProviders.of(this).get(NasabahViewModel.class);
        nasabahViewModel.init();
        SharedPreferences sharedPreferences = getSharedPreferences("com.mbank.android.logintoken", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("com.mbank.android.logintoken", "").equals("")){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }else {
            checkLogin(sharedPreferences.getString("com.mbank.android.logintoken", ""));
        }
    }

    void checkLogin(String token){
        nasabahViewModel.getNasabah(token).observe(this, new Observer<APIResponse>() {
            @Override
            public void onChanged(APIResponse apiResponse) {
                APIResponse response = apiResponse;
                try {
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Test 2 : Gagal!");
                }
            }
        });
    }
}