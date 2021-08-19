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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mg, parent, false);
        return new MyViewHolderNOK(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNOK.MyViewHolderNOK holder, int position) {

        TableNumberNOK tableNumberNOK = tableNumberNOKS.get(position);
        holder.bind(tableNumberNOK);

        holder.itemView.setOnClickListener(view -> {
            boolean expanded = tableNumberNOK.isExpanded();

            if(!tableNumberNOK.getExtra().equals("")){
                tableNumberNOK.setExpanded(!expanded);
                notifyItemChanged(position);
            }
        });

//        holder.textViewAn.setText(tableNumberNOKS.get(position).getAn()+"");
//        holder.textViewBn.setText(tableNumberNOKS.get(position).getBn()+"");
//        holder.textViewQn.setText(tableNumberNOKS.get(position).getQn()+"");
//        holder.textViewRn.setText(tableNumberNOKS.get(position).getRn()+"");
//
//        if(tableNumberNOKS.get(position).getBn()==0){
//            holder.textViewAn.setText("-");
//            holder.textViewBn.setText("-");
//            holder.textViewQn.setText("-");
//            holder.textViewRn.setText("-");
//        }
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
        private View subItem;
        private TextView textViewextra;

        public MyViewHolderNOK(@NonNull View itemView) {
            super(itemView);

            textViewAn = (TextView) itemView.findViewById(R.id.textViewAn);
            textViewBn = (TextView) itemView.findViewById(R.id.textViewBn);
            textViewQn = (TextView) itemView.findViewById(R.id.textViewQn);
            textViewRn = (TextView) itemView.findViewById(R.id.textViewRn);

            subItem = (View) itemView.findViewById(R.id.subItemGCD);

            textViewextra = (TextView) itemView.findViewById(R.id.extra_gcd);
        }

        private void bind(TableNumberNOK tableNumberNOK){

            boolean expanded = tableNumberNOK.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            textViewAn.setText(tableNumberNOK.getAn()+"");
            textViewBn.setText(tableNumberNOK.getBn()+"");
            textViewQn.setText(tableNumberNOK.getQn()+"");
            textViewRn.setText(tableNumberNOK.getRn()+"");

            textViewextra.setText(tableNumberNOK.getExtra());

            if(tableNumberNOK.getBn()==0){
                textViewAn.setText("-");
                textViewBn.setText("-");
                textViewQn.setText("-");
                textViewRn.setText("-");

            }

        }
    }
}
