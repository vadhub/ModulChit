package com.vad.modulchit.adapters;

import android.text.Editable;
import android.text.TextWatcher;
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
        return new MyViewHolder(v, new MyClassEditTextListener());
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRSAalphabyte.MyViewHolder holder, int position) {

        holder.listener.updatePosition(holder.getAdapterPosition());
        holder.textViewLetterAlpha.setText(shiphr.getAlphabyte().get(position)+"");
        holder.editTextNumber.setText(numbersCode.get(position)+"");

    }

    @Override
    public int getItemCount() {
        return numbersCode.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewLetterAlpha;
        EditText editTextNumber;
        MyClassEditTextListener listener;

        public MyViewHolder(@NonNull View itemView, MyClassEditTextListener listener) {
            super(itemView);

            this.listener = listener;

            textViewLetterAlpha = (TextView) itemView.findViewById(R.id.textViewLetterAlpha1);
            editTextNumber = (EditText) itemView.findViewById(R.id.editTextNumberAlpha);
            editTextNumber.addTextChangedListener(listener);
        }
    }

    private class MyClassEditTextListener implements TextWatcher{

        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(!charSequence.toString().equals("")){
                numbersCode.set(position, Integer.parseInt(charSequence.toString()));
            }
            System.out.println(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
