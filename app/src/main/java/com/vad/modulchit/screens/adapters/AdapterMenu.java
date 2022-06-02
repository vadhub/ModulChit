package com.vad.modulchit.screens.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vad.modulchit.R;

import java.util.List;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.MyViewHolder> {

    private List<String> strings;

    private OnItemMenuClickListener clickListener;

    public void setClickListener(OnItemMenuClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface OnItemMenuClickListener {
        void onClickMenu(View view, int position);
    }

    public List<String> getStrings() {
        return strings;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setStrings(List<String> strings) {
        this.strings = strings;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getStrings().size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MyViewHolder(v, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.menuName.setText(strings.get(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView menuName;

        public MyViewHolder(@NonNull View itemView, OnItemMenuClickListener clickListener) {
            super(itemView);
            menuName = itemView.findViewById(R.id.text_view_name_screen);

            itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onClickMenu(v, getAdapterPosition());
                }
            });
        }
    }
}
