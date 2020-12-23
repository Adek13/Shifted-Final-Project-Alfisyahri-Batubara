package com.mbank.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mbank.android.R;
import com.mbank.android.databinding.ActivityMessageBinding;


public class MessageActivity extends AppCompatActivity {

    private ActivityMessageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMessageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
    }

    void init(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            if (bundle.getInt("code")!=200){
                binding.errorImageView.setImageResource(R.drawable.ic_error);
            } else{
                binding.errorImageView.setImageResource(R.drawable.ic_undraw_verified_re_4io7);
            }
            binding.messageTextView.setText(bundle.getString("message", "Nothing Here!"));
            switch (bundle.getString("redirect", "")){
                case "dashboard":
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);
                    break;
                default:
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}