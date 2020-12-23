package com.mbank.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mbank.android.model.Mutasi;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import com.mbank.android.R;

public class MutasiAdapter extends RecyclerView.Adapter<MutasiAdapter.MutasiViewHolder> {

    Context context;
    ArrayList<Mutasi> mutasis;

    public MutasiAdapter(Context context, ArrayList<Mutasi> mutasis) {
        this.context = context;
        this.mutasis = mutasis;
    }

    @NonNull
    @Override
    public MutasiAdapter.MutasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_mutasi, parent, false);
        return new  MutasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MutasiAdapter.MutasiViewHolder holder, int position) {
        holder.tanggal.setText(mutasis.get(position).getWaktuMutasi().toString());
        holder.jenis.setText(mutasis.get(position).getJenisMutasi());
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        holder.jumlah.setText(kursIndonesia.format(mutasis.get(position).getJumlahMutasi()));
    }

    @Override
    public int getItemCount() {
        return mutasis.size();
    }

    public class MutasiViewHolder extends RecyclerView.ViewHolder{

        TextView tanggal;
        TextView jenis;
        TextView jumlah;

        public MutasiViewHolder(@NonNull View itemView) {
            super(itemView);

            tanggal = itemView.findViewById(R.id.tanggalTextView);
            jenis = itemView.findViewById(R.id.jenisTextView);
            jumlah = itemView.findViewById(R.id.jumlahTextView);

        }
    }
}
