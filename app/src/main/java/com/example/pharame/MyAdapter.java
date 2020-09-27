package com.example.pharame;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

import Data.MyAppDataBase;
import model.Medicine;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    private List<Medicine> medicineList;
    private List<Medicine> medicineListFiltered;
    private Activity context;
    private MyAppDataBase mydb;
    private OnMedListener onMedListener;


    public MyAdapter(Activity context, List<Medicine> medicineList, OnMedListener onMedListener) {
        this.context = context;
        this.medicineList = medicineList;
        this.medicineListFiltered = medicineList;
        this.onMedListener = onMedListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main, parent, false
                );
        return new ViewHolder(view, onMedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Medicine medicines = medicineList.get(position);
        mydb = MyAppDataBase.creatmydbInstance(context);
        String m = medicines.getMedname();
        String l=medicines.getLabo();
        holder.textView.setText(m+"/"+l);

        holder.btEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Medicine medicine = medicineList.get(holder.getAdapterPosition());
                final long mId = medicine.getMedid();
                String medname = medicine.getMedname();
                String medlabo = medicine.getLabo();
                String medpre = medicine.getPres();
                float medci = medicine.getCi();
                String medcii = String.valueOf(medci);
                float medcmax = medicine.getCmax();
                String medcmaxx = String.valueOf(medcmax);
                float medcmin = medicine.getCmin();
                String medcminn = String.valueOf(medcmin);
                float medprice = medicine.getPrice();
                String medpricee = String.valueOf(medprice);
                double medvlm = medicine.getVlm();
                String medvlmm = String.valueOf(medvlm);
                String medstab = medicine.getStab();


                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int hight = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setLayout(width, hight);
                dialog.show();

                final EditText name, labo, vlm, prix, ci, cmin, cmax, stabi, pres;
                Button btupdate = dialog.findViewById(R.id.bt_update);
                name = dialog.findViewById(R.id.edit_medname);
                labo = dialog.findViewById(R.id.edit_medlabo);
                prix = dialog.findViewById(R.id.edit_medprix);
                ci = dialog.findViewById(R.id.edit_medci);
                cmin = dialog.findViewById(R.id.edit_medcmin);
                cmax = dialog.findViewById(R.id.edit_medcmax);
                stabi = dialog.findViewById(R.id.edit_medstab);
                pres = dialog.findViewById(R.id.edit_medpres);
                vlm = dialog.findViewById(R.id.edit_medvlm);

                name.setText(medname);
                labo.setText(medlabo);
                prix.setText(medpricee);
                ci.setText(medcii);
                cmin.setText(medcminn);
                cmax.setText(medcmaxx);
                stabi.setText(medstab);
                pres.setText(medpre);
                vlm.setText(medvlmm);

                btupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        String uname = name.getText().toString().trim();
                        String ulabo = labo.getText().toString().trim();
                        String uprix = prix.getText().toString().trim();
                        String uci = ci.getText().toString().trim();
                        String ucmin = cmin.getText().toString().trim();
                        String ucmax = cmax.getText().toString().trim();
                        String ustabi = stabi.getText().toString().trim();
                        String upres = pres.getText().toString().trim();
                        String uvlm = vlm.getText().toString().trim();


                        mydb.getDao().updatename( mId, uname);
                        mydb.getDao().updatlabo(mId, ulabo);
                        mydb.getDao().updateprice(mId, uprix);
                        mydb.getDao().updateci(mId, uci);
                        mydb.getDao().updatecmin(mId, ucmin);
                        mydb.getDao().updatecmax(mId, ucmax);
                        mydb.getDao().updatestab(mId, ustabi);
                        mydb.getDao().updatpres(mId, upres);
                        mydb.getDao().updatevlm(mId, uvlm);

                        medicineList.clear();
                        medicineList.addAll(mydb.getDao().getAllmed());
                        notifyDataSetChanged();


                    }
                });


            }
        });
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Medicine medicine = medicineList.get(holder.getAdapterPosition());
                mydb.getDao().delete(medicine);
                int position = holder.getAdapterPosition();
                medicineList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, medicineList.size());

            }
        });

    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textView;
        ImageView btEdit, btDelete;
        OnMedListener onMedListener;

        public ViewHolder(@NonNull View itemView, OnMedListener onMedListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_medname);
            btEdit = itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);
            this.onMedListener = onMedListener;
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            onMedListener.onMedClick(getAdapterPosition());
        }
    }

    public interface OnMedListener {
        void onMedClick(int position);
    }
}
