package com.mbank.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mbank.android.Adapter.MutasiAdapter;
import com.mbank.android.R;
import com.mbank.android.databinding.ActivityMutasiBinding;
import com.mbank.android.model.Mutasi;
import com.mbank.android.model.MutasiRequest;
import com.mbank.android.viewmodel.MutasiViewModel;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MutasiActivity extends AppCompatActivity {

    ActivityMutasiBinding binding;

    MutasiViewModel mutasiViewModel;

    ArrayList<Mutasi> mutasiArrayList = new ArrayList<>();

    MutasiAdapter mutasiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMutasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        onClickGroup();
    }

    void init(){
        if (mutasiAdapter == null) {
            mutasiAdapter = new MutasiAdapter(getApplicationContext(), mutasiArrayList);
            binding.mutasiRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            binding.mutasiRecyclerView.setAdapter(mutasiAdapter);
            binding.mutasiRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.mutasiRecyclerView.setNestedScrollingEnabled(true);
        } else {
            mutasiAdapter.notifyDataSetChanged();
        }
        mutasiViewModel = ViewModelProviders.of(this).get(MutasiViewModel.class);
        mutasiViewModel.init();
    }

    void onClickGroup(){
        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView startDate = binding.startTextView;
                showDateDialog(startDate);
            }
        });
        binding.endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView endDate = binding.endTextView;
                showDateDialog(endDate);
            }
        });
        binding.muatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMutasi();
            }
        });
    }

    private void showDateDialog(TextView output){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.add(Calendar.DATE, -1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = year + "-" + (month+1) + "-" + day;
                output.setText(date);
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        calendar.add(Calendar.DATE, -30);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    void getMutasi(){
        SharedPreferences sharedPreferences = getSharedPreferences("com.mbank.android.logintoken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("com.mbank.android.logintoken", "");
        MutasiRequest mutasiRequest = new MutasiRequest(binding.startTextView.getText().toString(), binding.endTextView.getText().toString());
        mutasiViewModel.getMutasi(token, mutasiRequest).observe(this, mutasiResponse -> {
            System.out.println(mutasiResponse.getPayload());
            List<Mutasi> mutasis;
            Type listType = new TypeToken<List<Mutasi>>(){}.getType();
            mutasis = new Gson().fromJson(mutasiResponse.getPayload(), listType);
            mutasiArrayList.clear();
            mutasiArrayList.addAll(mutasis);
            mutasiAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back_only_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.backMenuItem:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}