package com.example.student.readfiles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.MyViewHolder> {

    private final Context context;
    private final List<ModelInfo> list;

    public CarsAdapter(final Context context, final List<ModelInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CarsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.car_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsAdapter.MyViewHolder holder, int position) {
        holder.textMarka.setText(list.get(position).getTextMarka());
        holder.textModel.setText(list.get(position).getTextModel());
        holder.textPrice.setText(list.get(position).getTextPrice());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView textMarka;
        private final TextView textModel;
        private final TextView textPrice;


        private MyViewHolder(View itemView) {
            super(itemView);
            textMarka = itemView.findViewById(R.id.marka_text);
            textModel = itemView.findViewById(R.id.model_text);
            textPrice = itemView.findViewById(R.id.price_text);

        }
    }
}
