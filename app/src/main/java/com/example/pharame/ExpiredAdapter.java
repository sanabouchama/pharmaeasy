package com.example.pharame;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import Data.MyAppDataBase;
import model.Medicine;
import model.Remainder;

public class ExpiredAdapter extends RecyclerView.Adapter<ExpiredAdapter.ViewHolder> {


    private MyAppDataBase dataBase;
    private List<Remainder> reminderList;
    private Activity context ;




    public ExpiredAdapter(Activity context, List<Remainder> remainderList){
        this.context=context;
        this.reminderList=remainderList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listmedperim_row_main, parent, false
                );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        dataBase = MyAppDataBase.creatmydbInstance(context);
        final Remainder remainder = reminderList.get(position);
        long mid= remainder.getMedId();
        Medicine medicine=dataBase.getDao().getMedr(mid);
        holder.medname.setText(medicine.getMedname());
        holder.remainder.setText("reliquat: "+Double.toString(remainder.getRemainder())+" ml");
        double price =(double) Math.round((remainder.getRemainder()*medicine.getPrice()) * 100) / 100;
        holder.prix.setText("prix: "+price+" da");
        holder.delete.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Remainder remainder1 = reminderList.get(holder.getAdapterPosition());
              remainder1.setRemainder(0.0);
              int position=holder.getAdapterPosition();
              reminderList.remove(position);
              notifyItemRemoved(position);
              notifyItemRangeChanged(position,reminderList.size());
          }
      });
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView medname,remainder,prix;
        ImageButton delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medname =itemView.findViewById(R.id.textView7);
            remainder=itemView.findViewById(R.id.textView9);
            prix = itemView.findViewById(R.id.textView10);
            delete=itemView.findViewById(R.id.imageButton);
        }
    }
}

