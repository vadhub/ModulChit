package com.vad.modulchit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vad.modulchit.R;
import com.vad.modulchit.utils.RSAshiphr;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AdapterRSAalphabyte extends RecyclerView.Adapter<AdapterRSAalphabyte.MyViewHolder> {

    private List<Integer> numbersCode;
    private RSAshiphr shiphr;

    public AdapterRSAalphabyte() {
        shiphr = new RSAshiphr();
    }

    public List<Integer> getNumbersCode() {
        return numbersCode;
    }

    public void setNumbersCode(List<Integer> numbersCode) {
        this.numbersCode = numbersCode;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alphavite_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRSAalphabyte.MyViewHolder holder, int position) {

        holder.textViewLetter.setText(shiphr.getAlphabyte().get(position));
        holder.editTextNumber.setText(numbersCode.get(position));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewLetter;
        private EditText editTextNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewLetter = (TextView) itemView.findViewById(R.id.textViewLitter);
            textViewLetter = (TextView) itemView.findViewById(R.id.editTextNumber);
        }
    }
}
