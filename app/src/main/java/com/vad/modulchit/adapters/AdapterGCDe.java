package com.vad.modulchit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vad.modulchit.R;
import com.vad.modulchit.pojos.TableNumberGCDe;

import java.util.List;

public class AdapterGCDe extends RecyclerView.Adapter<AdapterGCDe.MyViewHolder> {

    private List<TableNumberGCDe> tableNumberGCDes;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<TableNumberGCDe> getTableNumbers() {
        return tableNumberGCDes;
    }

    public void setTableNumbers(List<TableNumberGCDe> tableNumberGCDes) {
        this.tableNumberGCDes = tableNumberGCDes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterGCDe.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.tablet_counter, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterGCDe.MyViewHolder holder, int position) {
        holder.textViewA.setText(tableNumberGCDes.get(position).getA()+"");
        holder.textViewB.setText(tableNumberGCDes.get(position).getB()+"");
        holder.textViewQ.setText(tableNumberGCDes.get(position).getQ()+"");
        holder.textViewR.setText(tableNumberGCDes.get(position).getR()+"");

        holder.textViewX.setText("("+ tableNumberGCDes.get(position).getX1()+"; "+ tableNumberGCDes.get(position).getX2()+")");
        holder.textViewY.setText("("+ tableNumberGCDes.get(position).getY1()+"; "+ tableNumberGCDes.get(position).getY2()+")");

    }

    @Override
    public int getItemCount() {
        return tableNumberGCDes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewA;
        TextView textViewB;
        TextView textViewQ;
        TextView textViewR;

        TextView textViewX;
        TextView textViewY;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            onItemClickListener.onClickItem(getAdapterPosition());
            itemView.setOnClickListener(this);
            textViewA = (TextView) itemView.findViewById(R.id.textViewA);
            textViewB = (TextView) itemView.findViewById(R.id.textViewB);
            textViewQ = (TextView) itemView.findViewById(R.id.textViewQ);
            textViewR = (TextView) itemView.findViewById(R.id.textViewR);

            textViewX = (TextView) itemView.findViewById(R.id.textViewX);
            textViewY = (TextView) itemView.findViewById(R.id.textViewY);

        }

        @Override
        public void onClick(View view) {
            if(onItemClickListener!=null){
                onItemClickListener.onClickItem(getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener{
        void onClickItem(int position);
    }
}
