package com.example.pharame;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Data.MyAppDataBase;
import model.Medicine;
import model.Patient;
import model.Preparation;
import model.Remainder;

public class PrepaAdapter extends RecyclerView.Adapter<PrepaAdapter.ViewHolder> {
    private List<Preparation> preparations;
    private Activity context;
    private MyAppDataBase mydb;


    public PrepaAdapter(Activity context, List<Preparation> preparations) {
        this.context = context;
        this.preparations = preparations;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listprepa_row_main, parent, false
                );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        final Preparation preparation = preparations.get(position);
        mydb = MyAppDataBase.creatmydbInstance(context);
        holder.numprepa.setText("Preparation " + preparation.getpId());
        final long paId = preparation.getPatId();
        String firstname = mydb.getDao().nompat(paId);
        holder.nompatient.setText(firstname);
        String lastname = mydb.getDao().prepat(paId);
        holder.prenompatient.setText(lastname);
        long mID = preparation.getmId();
        String medname = mydb.getDao().namemed(mID);
        holder.med.setText(medname);
        Date date = preparation.getDate();
        DateFormat df = new SimpleDateFormat("HH:mm");
        String datii = df.format(date);
        holder.prepatime.setText(datii);

        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preparation p = preparations.get(holder.getAdapterPosition());
                Patient patient = mydb.getDao().getpatt(p.getPatId());
                String firstname = patient.getFirstName();
                String lastname = patient.getLastName();
                Double weight = patient.getWeight();
                Double height = patient.getHeight();
                Float bs = patient.getBodysurface();

                Float daa = p.getDaa();
                Float vaa = p.getVaa();

                Intent intent = new Intent(context, PreparationInfo.class);
                intent.putExtra("nom", firstname);
                intent.putExtra("pre", lastname);
                intent.putExtra("weight", String.valueOf(weight));
                intent.putExtra("height", String.valueOf(height));
                intent.putExtra("bs", String.valueOf(bs));
                intent.putExtra("daa", String.valueOf(daa));
                intent.putExtra("vaa", String.valueOf(vaa));
                context.startActivity(intent);
                context.finish();


            }
        });


    }

    @Override
    public int getItemCount() {
        return preparations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView numprepa, nompatient, prenompatient, med, prepatime;
        ImageButton info;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numprepa = itemView.findViewById(R.id.numprepara);
            nompatient = itemView.findViewById(R.id.nompatient);
            prenompatient = itemView.findViewById(R.id.prepatient);
            med = itemView.findViewById(R.id.mednamee);
            prepatime = itemView.findViewById(R.id.prepatimee);

            info = itemView.findViewById(R.id.imageButton);

        }
    }
}

