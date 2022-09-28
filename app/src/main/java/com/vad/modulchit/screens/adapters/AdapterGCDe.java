package com.vad.modulchit.screens.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vad.modulchit.R;
import com.vad.modulchit.models.pojos.TableNumberGCDe;

import java.util.List;

public class AdapterGCDe extends RecyclerView.Adapter<AdapterGCDe.MyViewHolder> {

    private List<TableNumberGCDe> tableNumberGCDes;

    @SuppressLint("NotifyDataSetChanged")
    public void setTableNumbers(List<TableNumberGCDe> tableNumberGCDes) {
        this.tableNumberGCDes = tableNumberGCDes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterGCDe.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_gcde, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterGCDe.MyViewHolder holder, int position) {

        boolean paint = position % 2 == 0 && position != tableNumberGCDes.size();

        TableNumberGCDe tableNumberGCDe = tableNumberGCDes.get(position);
        holder.bind(tableNumberGCDe, paint);

        holder.itemView.setOnClickListener(view -> {
            if (!tableNumberGCDe.extraIsEmpty()) {
                tableNumberGCDe.rollUp();
                notifyItemChanged(position);
            }

        });
    }

    @Override
    public int getItemCount() {
        return tableNumberGCDes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewA;
        TextView textViewB;
        TextView textViewQ;
        TextView textViewR;

        TextView textViewX;
        TextView textViewY;

        TextView textViewExtra;

        View subItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewA = (TextView) itemView.findViewById(R.id.textViewA);
            textViewB = (TextView) itemView.findViewById(R.id.textViewB);
            textViewQ = (TextView) itemView.findViewById(R.id.textViewQ);
            textViewR = (TextView) itemView.findViewById(R.id.textViewR);
            textViewX = (TextView) itemView.findViewById(R.id.textViewX);
            textViewY = (TextView) itemView.findViewById(R.id.textViewY);
            textViewExtra = (TextView) itemView.findViewById(R.id.extra_gcde);

            subItem = (View) itemView.findViewById(R.id.subItem);
        }

        @SuppressLint("ResourceAsColor")
        private void bind(TableNumberGCDe gcde, boolean paint) {

            gcde.fillRowTable(textViewA, textViewB, textViewQ, textViewR, textViewX, textViewY, textViewExtra);

            // Get the state
            boolean expanded = gcde.isExpand();
            // Set the visibility based on state
            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            if (paint) {
                textViewA.setBackgroundResource(R.color.textViewColor);
                textViewB.setBackgroundResource(R.color.textViewColor);
                textViewQ.setBackgroundResource(R.color.textViewColor);
                textViewR.setBackgroundResource(R.color.textViewColor);
                textViewX.setBackgroundResource(R.color.textViewColor);
                textViewY.setBackgroundResource(R.color.textViewColor);
            }
        }
    }
}
