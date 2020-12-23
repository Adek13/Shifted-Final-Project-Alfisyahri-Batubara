package com.mbank.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mbank.android.databinding.ActivityLoginBinding;
import com.mbank.android.model.LoginRequest;
import com.mbank.android.model.APIResponse;
import com.mbank.android.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        onClickGroup();
        SharedPreferences sharedPreferences = getSharedPreferences(getBaseContext().toString(), Context.MODE_PRIVATE);
        System.out.println("token : " + sharedPreferences.getString("com.mbank.android.logintoken", ""));
    }

    void init(){
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.init();
    }

    void onClickGroup(){
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    void doLogin(){
        String email = binding.emailEditText.getText().toString();
        String password = binding.passwordEditText.getText().toString();
        LoginRequest loginRequest = new LoginRequest(email, password);
        userViewModel.postLogin(loginRequest).observe(this, apiResponse -> {
            APIResponse response = apiResponse;
            if (response.getCode()==200){
                SharedPreferences sharedPreferences = getSharedPreferences("com.mbank.android.logintoken", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (!sharedPreferences.getString("com.mbank.android.logintoken", "").equals("")){
                    editor.putString("com.mbank.android.logintoken", "");
                    editor.apply();
                }
                editor.putString("com.mbank.android.logintoken", response.getPayload());
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
                finish();
            }else{
                new MaterialAlertDialogBuilder(this)
                        .setTitle("Gagal")
                        .setMessage("Email Atau Password Salah!")
                        .setNegativeButton("Close", null)
                        .show();
            }
        });
    }
}