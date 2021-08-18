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

        TableNumberFE tableNumberFE = tableNumberFES.get(position);
        holder.bind(tableNumberFE);

        holder.itemView.setOnClickListener(view -> {
            boolean expanded = tableNumberFE.isExpanded();

            if(!tableNumberFE.getExtra().equals("")){
                tableNumberFE.setExpanded(!expanded);
                notifyItemChanged(position);
            }
        });

//        holder.textViewA.setText(tableNumberFES.get(position).getAfe()+"");
//        holder.textViewM.setText(tableNumberFES.get(position).getM()+"");
//        holder.textViewN.setText(tableNumberFES.get(position).getN()+"");
//        holder.textViewP.setText(tableNumberFES.get(position).getP()+"");
//        holder.textViewR.setText(tableNumberFES.get(position).getRfe()+"");
//
//        if(tableNumberFES.get(position).getAfe() == -1 && tableNumberFES.get(position).getN()==-1){
//            holder.textViewA.setText("");
//            holder.textViewM.setText("");
//            holder.textViewN.setText("");
//            holder.textViewP.setText("");
//            holder.textViewR.setText("");
//        }
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

        private void bind(TableNumberFE tableNumberFE){
            boolean expanded = tableNumberFE.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            textViewA.setText(tableNumberFE.getAfe()+"");
            textViewM.setText(tableNumberFE.getM()+"");
            textViewN.setText(tableNumberFE.getN()+"");
            textViewP.setText(tableNumberFE.getP()+"");
            textViewR.setText(tableNumberFE.getRfe()+"");

            textViewExtra.setText(tableNumberFE.getExtra());

            if(tableNumberFE.getAfe() == -1 && tableNumberFE.getN()==-1){
                textViewA.setText("");
                textViewM.setText("");
                textViewN.setText("");
                textViewP.setText("");
                textViewR.setText("");
            }
        }
    }
}
