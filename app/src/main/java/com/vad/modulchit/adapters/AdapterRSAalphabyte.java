package com.vad.modulchit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vad.modulchit.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AdapterRSAalphabyte extends RecyclerView.Adapter<AdapterRSAalphabyte.MyViewHolder> {

    private LinkedHashMap<Character, Integer> hashMap;

    public LinkedHashMap<Character, Integer> getHashMap() {
        return hashMap;
    }

    public void setHashMap(LinkedHashMap<Character, Integer> hashMap) {
        this.hashMap = hashMap;
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

        //get letters from linkedhashset at keyset
        List<Character> letters = new ArrayList<>(hashMap.keySet());

        //get numbers from linkedhashset at values
        List<Integer> numbers = new ArrayList<>(hashMap.values());

        holder.textViewLetter.setText(letters.get(position));
        holder.editTextNumber.setText(numbers.get(position));


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
