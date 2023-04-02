package com.vad.modulchit.screens.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vad.modulchit.R;
import com.vad.modulchit.models.pojos.TableNumberNOK;

import java.util.List;

public class AdapterNOK extends RecyclerView.Adapter<AdapterNOK.MyViewHolderNOK> {

    private List<TableNumberNOK> tableNumberNOKS;

    @SuppressLint("NotifyDataSetChanged")
    public void setTableNumberNOKS(List<TableNumberNOK> tableNumberNOKS) {
        this.tableNumberNOKS = tableNumberNOKS;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolderNOK onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mg, parent, false);
        return new MyViewHolderNOK(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNOK.MyViewHolderNOK holder, int position) {

        TableNumberNOK tableNumberNOK = tableNumberNOKS.get(position);
        holder.bind(tableNumberNOK);

        holder.itemView.setOnClickListener(view -> {
            if (!tableNumberNOK.extraIsEmpty()) {
                tableNumberNOK.rollUp();
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tableNumberNOKS.size();
    }

    public static class MyViewHolderNOK extends RecyclerView.ViewHolder {

        private final TextView textViewAn;
        private final TextView textViewBn;
        private final TextView textViewQn;
        private final TextView textViewRn;
        private final View subItem;
        private final TextView textViewExtra;

        public MyViewHolderNOK(@NonNull View itemView) {
            super(itemView);

            textViewAn = itemView.findViewById(R.id.textViewAn);
            textViewBn = itemView.findViewById(R.id.textViewBn);
            textViewQn = itemView.findViewById(R.id.textViewQn);
            textViewRn = itemView.findViewById(R.id.textViewRn);

            subItem = itemView.findViewById(R.id.subItemGCD);

            textViewExtra = itemView.findViewById(R.id.extra_gcd);
        }

        @SuppressLint("ResourceAsColor")
        private void bind(TableNumberNOK tableNumberNOK) {

            boolean expanded = tableNumberNOK.isExpand();

            Animation animationFadeIn = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.animation_fade_in);

            if (expanded) {
                subItem.setVisibility(View.VISIBLE);
                subItem.startAnimation(animationFadeIn);
            } else {
                subItem.setVisibility(View.GONE);
            }

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            tableNumberNOK.fillRowTable(textViewAn, textViewBn, textViewQn, textViewRn, textViewExtra);

        }
    }
}
