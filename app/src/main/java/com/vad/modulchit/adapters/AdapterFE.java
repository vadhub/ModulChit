package com.vad.modulchit.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vad.modulchit.pojos.TableNumberFE;

import java.util.List;

public class AdapterFE extends RecyclerView.Adapter<AdapterFE.MyViewHolderFE> {

    private List<TableNumberFE> tableNumberFES;

    public List<TableNumberFE> getTableNumberFES() {
        return tableNumberFES;
    }

    public void setTableNumberFES(List<TableNumberFE> tableNumberFES) {
        this.tableNumberFES = tableNumberFES;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolderFE onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFE.MyViewHolderFE holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolderFE extends RecyclerView.ViewHolder {
        public MyViewHolderFE(@NonNull View itemView) {
            super(itemView);
        }
    }
}
