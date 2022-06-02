package com.vad.modulchit.screens.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vad.modulchit.R;
import com.vad.modulchit.models.pojos.TableNumberFE;

import java.util.List;

public class AdapterFE extends RecyclerView.Adapter<AdapterFE.MyViewHolderFE> {

    private List<TableNumberFE> tableNumberFES;

    public List<TableNumberFE> getTableNumberFES() {
        return tableNumberFES;
    }

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
            boolean expanded = tableNumberFE.isExpanded();

            if (!tableNumberFE.getExtra().equals("")) {
                tableNumberFE.setExpanded(!expanded);
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tableNumberFES.size();
    }

    public class MyViewHolderFE extends RecyclerView.ViewHolder {

        TextView textViewA;
        TextView textViewM;
        TextView textViewN;
        TextView textViewP;
        TextView textViewR;

        TextView textViewExtra;

        View subItem;

        public MyViewHolderFE(@NonNull View itemView) {
            super(itemView);

            textViewA = (TextView) itemView.findViewById(R.id.textViewAfe);
            textViewM = (TextView) itemView.findViewById(R.id.textViewMfe);
            textViewN = (TextView) itemView.findViewById(R.id.textViewNfe);
            textViewP = (TextView) itemView.findViewById(R.id.textViewPfe);
            textViewR = (TextView) itemView.findViewById(R.id.textViewRfe);

            textViewExtra = (TextView) itemView.findViewById(R.id.extra_fe);

            subItem = (View) itemView.findViewById(R.id.subItemFE);
        }

        @SuppressLint("ResourceAsColor")
        private void bind(TableNumberFE tableNumberFE, boolean paint) {
            boolean expanded = tableNumberFE.isExpanded();

            if (paint) {
                textViewA.setBackgroundResource(R.color.textViewColor);
                textViewM.setBackgroundResource(R.color.textViewColor);
                textViewN.setBackgroundResource(R.color.textViewColor);
                textViewP.setBackgroundResource(R.color.textViewColor);
                textViewR.setBackgroundResource(R.color.textViewColor);
            }
            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            textViewA.setText(tableNumberFE.getAfe() + "");
            textViewM.setText(tableNumberFE.getM() + "");
            textViewN.setText(tableNumberFE.getN() + "");
            textViewP.setText(tableNumberFE.getP() + "");
            textViewR.setText(tableNumberFE.getRfe() + "");

            textViewExtra.setText(tableNumberFE.getExtra());

            if (tableNumberFE.getAfe() == -1 && tableNumberFE.getN() == -1) {
                textViewA.setText("");
                textViewM.setText("");
                textViewN.setText("");
                textViewP.setText("");
                textViewR.setText("");
            }
        }
    }
}
