package com.mbank.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mbank.android.R;
import com.mbank.android.databinding.ActivityRegisterBinding;
import com.mbank.android.model.RegisterRequest;
import com.mbank.android.model.APIResponse;
import com.mbank.android.viewmodel.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        onClickGroup();
    }

    void onClickGroup(){
        binding.registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                doRegister();
            }
        });
    }

    private void init(){
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.init();
    }

    private void doRegister(){
        String noRekening = binding.noRekeningEditText.getText().toString();
        String email = binding.emailEditText.getText().toString();
        String noTelepon = binding.noTeleponEditText.getText().toString();
        String password = binding.passwordEditText.getText().toString();
        String ulangPassword = binding.ulangPasswordEditText.getText().toString();

        if (password.equals("")||ulangPassword.equals("")){
            Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
        }else{
            if (password.equals(ulangPassword)){
                RegisterRequest registerRequest = new RegisterRequest(noRekening, email, noTelepon, password);
                userViewModel.postRegister(registerRequest).observe(this, nasabahResponse -> {
                    APIResponse response = nasabahResponse;
                    if (response.getCode()==200){
                        moveToMessageActivity("Succeed", "Selamat Anda Terdaftar!", 200);
                    }else{
                        System.out.println(response.getPayload());
                        showAlert(String.valueOf(response.getPayload()));
//                        moveToMessageActivity("error", response.getPayload(), response.getCode());
                    }
                });
            }else{
                Toast.makeText(getApplicationContext(), "Password Tidak Sama!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void moveToMessageActivity(String type, String message, int code){
        Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putInt("code", code);
        bundle.putString("message", message);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    void showAlert(String message){
        new MaterialAlertDialogBuilder(this)
                .setMessage(message)
                .setIcon(R.drawable.ic_baseline_email_24)
                .setNegativeButton("Close", null)
                .show();
    }
}