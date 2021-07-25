package com.vad.modulchit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vad.modulchit.R;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_counter_fe, parent, false);
        return new MyViewHolderFE(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFE.MyViewHolderFE holder, int position) {
        holder.textViewA.setText(tableNumberFES.get(position).getAfe()+"");
        holder.textViewM.setText(tableNumberFES.get(position).getM()+"");
        holder.textViewN.setText(tableNumberFES.get(position).getN()+"");
        holder.textViewP.setText(tableNumberFES.get(position).getP()+"");
        holder.textViewR.setText(tableNumberFES.get(position).getRfe()+"");

        if(tableNumberFES.get(position).getAfe() == -1 && tableNumberFES.get(position).getN()==-1){
            holder.textViewA.setText("");
            holder.textViewM.setText("");
            holder.textViewN.setText("");
            holder.textViewP.setText("");
            holder.textViewR.setText("");
        }
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


        public MyViewHolderFE(@NonNull View itemView) {
            super(itemView);

            textViewA = (TextView) itemView.findViewById(R.id.textViewAfe);
            textViewM = (TextView) itemView.findViewById(R.id.textViewMfe);
            textViewN = (TextView) itemView.findViewById(R.id.textViewNfe);
            textViewP = (TextView) itemView.findViewById(R.id.textViewPfe);
            textViewR = (TextView) itemView.findViewById(R.id.textViewRfe);
        }
    }
}
