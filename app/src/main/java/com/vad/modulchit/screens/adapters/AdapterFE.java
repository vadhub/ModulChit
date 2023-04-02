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
import com.vad.modulchit.models.pojos.TableNumberFE;

import java.util.List;

public class AdapterFE extends RecyclerView.Adapter<AdapterFE.MyViewHolderFE> {

    private List<TableNumberFE> tableNumberFES;

    public void setTableNumberFES(List<TableNumberFE> tableNumberFES) {
        this.tableNumberFES = tableNumberFES;
        notifyItemChanged(5);
    }

    @NonNull
    @Override
    public MyViewHolderFE onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fe, parent, false);
        return new MyViewHolderFE(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFE.MyViewHolderFE holder, int position) {

        boolean paint = position % 2 == 0 && position != tableNumberFES.size();
        TableNumberFE tableNumberFE = tableNumberFES.get(position);
        holder.bind(tableNumberFE, paint);

        holder.itemView.setOnClickListener(view -> {

            if (!tableNumberFE.extraIsEmpty()) {
                tableNumberFE.rollUp();
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tableNumberFES.size();
    }

    public static class MyViewHolderFE extends RecyclerView.ViewHolder {

        private final TextView textViewA;
        private final TextView textViewM;
        private final TextView textViewN;
        private final TextView textViewP;
        private final TextView textViewR;

        private final TextView textViewExtra;

        private final View subItem;

        public MyViewHolderFE(@NonNull View itemView) {
            super(itemView);

            textViewA = itemView.findViewById(R.id.textViewAfe);
            textViewM = itemView.findViewById(R.id.textViewMfe);
            textViewN = itemView.findViewById(R.id.textViewNfe);
            textViewP = itemView.findViewById(R.id.textViewPfe);
            textViewR = itemView.findViewById(R.id.textViewRfe);

            textViewExtra = itemView.findViewById(R.id.extra_fe);

            subItem = itemView.findViewById(R.id.subItemFE);
        }

        @SuppressLint("ResourceAsColor")
        private void bind(TableNumberFE tableNumberFE, boolean paint) {

            tableNumberFE.fillRowTable(textViewA, textViewM, textViewN, textViewP, textViewR, textViewExtra);

            Animation animationFadeIn = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.animation_fade_in);

            if (tableNumberFE.isExpand()) {
                subItem.setVisibility(View.VISIBLE);
                subItem.startAnimation(animationFadeIn);
            } else {
                subItem.setVisibility(View.GONE);
            }


            if (paint) {
                textViewA.setBackgroundResource(R.color.textViewColor);
                textViewM.setBackgroundResource(R.color.textViewColor);
                textViewN.setBackgroundResource(R.color.textViewColor);
                textViewP.setBackgroundResource(R.color.textViewColor);
                textViewR.setBackgroundResource(R.color.textViewColor);
            }
        }
    }
}
