package com.vad.modulchit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vad.modulchit.R;
import com.vad.modulchit.pojos.TableNumberNOK;

import java.util.List;

public class AdapterNOK extends RecyclerView.Adapter<AdapterNOK.MyViewHolderNOK> {

    private List<TableNumberNOK> tableNumberNOKS;

    public List<TableNumberNOK> getTableNumberNOKS() {
        return tableNumberNOKS;
    }

    public void setTableNumberNOKS(List<TableNumberNOK> tableNumberNOKS) {
        this.tableNumberNOKS = tableNumberNOKS;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolderNOK onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_counter_nok, parent, false);
        return new MyViewHolderNOK(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNOK.MyViewHolderNOK holder, int position) {
        holder.textViewAn.setText(tableNumberNOKS.get(position).getAn()+"");
        holder.textViewBn.setText(tableNumberNOKS.get(position).getBn()+"");
        holder.textViewQn.setText(tableNumberNOKS.get(position).getQn()+"");
        holder.textViewRn.setText(tableNumberNOKS.get(position).getRn()+"");

        if(tableNumberNOKS.get(position).getBn()==0){
            holder.textViewAn.setText("-");
            holder.textViewBn.setText("-");
            holder.textViewQn.setText("-");
            holder.textViewRn.setText("-");
        }
    }

    @Override
    public int getItemCount() {
        return tableNumberNOKS.size();
    }

    public class MyViewHolderNOK extends RecyclerView.ViewHolder {

        private TextView textViewAn;
        private TextView textViewBn;
        private TextView textViewQn;
        private TextView textViewRn;

        public MyViewHolderNOK(@NonNull View itemView) {
            super(itemView);

            textViewAn = (TextView) itemView.findViewById(R.id.textViewAn);
            textViewBn = (TextView) itemView.findViewById(R.id.textViewBn);
            textViewQn = (TextView) itemView.findViewById(R.id.textViewQn);
            textViewRn = (TextView) itemView.findViewById(R.id.textViewRn);
        }
    }
}
