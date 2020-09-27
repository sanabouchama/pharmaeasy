package com.example.pharame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Data.MyAppDataBase;
import Data.MyDao;
import model.Medicine;
import model.Patient;
import model.Preparation;
import model.Remainder;


public class Calculation extends AppCompatActivity {


    private Spinner spinner;

    Button calculbtn;
    MyDao myDao;
    EditText srfc, poso, redu, firstname, lastname, height, weight;
    TextView daa, vaa, nfla, reli;
    List<Preparation> preparationList = new ArrayList<>();
    MyAppDataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        calculbtn = findViewById(R.id.buttoncalcul);


        redu = findViewById(R.id.redi);
        srfc = findViewById(R.id.psc);
        poso = findViewById(R.id.poso);
        firstname = findViewById(R.id.pname);
        lastname = findViewById(R.id.ppren);
        height = findViewById(R.id.ptaille);
        weight = findViewById(R.id.ppoid);


        daa = findViewById(R.id.daatx);
        vaa = findViewById(R.id.vaatx);
        nfla = findViewById(R.id.nbrftx);
        reli = findViewById(R.id.relitx);

        dataBase = MyAppDataBase.creatmydbInstance(this);


        final List<Medicine> medicines = dataBase.creatmydbInstance(this).getDao().getAllmed();
        spinner = findViewById(R.id.spinner);
        final ArrayAdapter<Medicine> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, medicines);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Medicine medicine = (Medicine) spinner.getSelectedItem();
                displaymedData(medicine);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        calculbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty()) {
                    new Handler().postDelayed(new Runnable() {




                        @Override
                        public void run() {

                            float reduction = Float.parseFloat(redu.getText().toString());
                            final float posologie = Float.parseFloat(poso.getText().toString());
                            final float surfacecorpo = Float.parseFloat(srfc.getText().toString());
                            float DAA;
                            float VAA;
                            double nflacon;
                            double reliquat;
                            Date date = Calendar.getInstance().getTime();
                            final Medicine medicine = (Medicine) spinner.getSelectedItem();
                            long mId = medicine.getMedid();
                            final Remainder remainder = dataBase.getDao().getrema(mId);
                            float ci = medicine.getCi();
                            if((remainder.getRemainderperim()!=null)){
                            if( (remainder.getRemainderperim().before(Calendar.getInstance().getTime()))&&(remainder.getRemainder()>0)) {
                                Toast.makeText(Calculation.this, "le reliquat est périmé et il vas étre supprimée", Toast.LENGTH_LONG).show();
                                dataBase.getDao().updateremaindeer(remainder.getIdreli(), 0.0);
                                remainder.setRemainder(0.0);
                            }}
                                DAA = (surfacecorpo * posologie * reduction);
                                VAA = DAA / ci;


                                if (remainder.getRemainder() >= VAA) {
                                    nflacon = 0;
                                    double reliquatt = remainder.getRemainder() - VAA;
                                    reliquat = (double) Math.round(reliquatt * 100) / 100;
                                    remainder.setRemainder(reliquat);
                                    dataBase.getDao().updateremaindeer(mId, reliquat);
                                    dataBase.getDao().updateremaindeer(remainder.getIdreli(), reliquat);

                                    if (remainder.getRemainder() == 0) {
                                        remainder.setRemainderdate(null);
                                        dataBase.getDao().updateremaindeerdate(remainder.getIdreli(), null);
                                        remainder.setRemainderperim(null);
                                        dataBase.getDao().updateremainderdateperim(remainder.getIdreli(), null);
                                    }


                                } else {
                                    double nflaconn = (VAA - remainder.getRemainder()) / medicine.getVlm();
                                    nflacon = Math.ceil(nflaconn);
                                    double reliquatt = (nflacon * medicine.getVlm()) - (VAA - remainder.getRemainder());
                                    reliquat = (double) Math.round(reliquatt * 100) / 100;

                                    remainder.setRemainder(reliquat);
                                    dataBase.getDao().updateremaindeer(remainder.getIdreli(), reliquat);
                                    remainder.setRemainder(reliquat);
                                    if (reliquat != 0) {
                                        remainder.setRemainderdate(date);
                                        dataBase.getDao().updateremaindeerdate(remainder.getIdreli(), date);
                                        int stab = Integer.parseInt(medicine.getStab());
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.setTime(remainder.getRemainderdate());
                                        calendar.add(Calendar.HOUR, stab);
                                        Date dateperim = calendar.getTime();
                                        remainder.setRemainderperim(dateperim);
                                        dataBase.getDao().updateremainderdateperim(remainder.getIdreli(), dateperim);

                                    } else {
                                        remainder.setRemainderdate(null);
                                        dataBase.getDao().updateremaindeerdate(remainder.getIdreli(), null);
                                        remainder.setRemainderperim(null);
                                        dataBase.getDao().updateremainderdateperim(remainder.getIdreli(), null);
                                    }

                                }


                                float mp111 = DAA / (medicine.getCmin());
                                float mp222 = DAA / (medicine.getCmax());
                                int mp1 = Math.round(mp111 * 100) / 100;
                                int mp2 = Math.round(mp222 * 100) / 100;
                                String mp11 = Integer.toString(mp1);
                                String mp22 = Integer.toString(mp2);
                                String mpoche = ("[" + mp22 + "-" + mp11 + "]");


                                final String firstnamep = firstname.getText().toString();
                                final String lastnamep = lastname.getText().toString();
                                final double heightp = Double.parseDouble(height.getText().toString());
                                final double weightp = Double.parseDouble(weight.getText().toString());

                                Patient patient = new Patient(firstnamep, lastnamep, heightp, weightp, surfacecorpo, posologie);

                                long patId = dataBase.getDao().addpatient(patient);
                                BroadcastReceiver mReceiver = new BroadcastReceiver() {
                                    @Override
                                    public void onReceive(Context context, Intent intent) {
                                        preparationList = (List<Preparation>) intent.getSerializableExtra("LIST2");
                                    }
                                };
                                LocalBroadcastManager.getInstance(Calculation.this).registerReceiver(mReceiver, new IntentFilter("INTENT_NAME"));
                                PrepaAdapter adapter1 = new PrepaAdapter(Calculation.this, preparationList);
                                Date datte = Calendar.getInstance().getTime();
                                Preparation preparation = new Preparation(mId, patId, datte, DAA, VAA, reduction);
                                long pId = dataBase.getDao().addparepa(preparation);

                                preparationList.clear();
                                preparationList.addAll(dataBase.getDao().getAllpreparation());
                                adapter1.notifyDataSetChanged();


                                String daaa = Float.toString(DAA);
                                String vaaa = Float.toString(VAA);
                                String nflaa = Double.toString(nflacon);
                                String relii = Double.toString(remainder.getRemainder());
                                String prepaid = Long.toString(pId);

                                DateFormat df = new SimpleDateFormat("dd-MM-yyyy ");
                                String datee = df.format(preparation.getDate().getTime());


                                Intent inte = new Intent(Calculation.this, Resultats.class);
                                inte.putExtra("daa", daaa);
                                inte.putExtra("vaa", vaaa);
                                inte.putExtra("nfla", nflaa);
                                inte.putExtra("reli", relii);
                                inte.putExtra("poche", mpoche);
                                inte.putExtra("prepaid", prepaid);
                                inte.putExtra("prepadate", datee);
                                startActivity(inte);
                                finish();



                        }
                    }, 1000);

                } else {
                    Toast.makeText(Calculation.this, "Champ vide ", Toast.LENGTH_SHORT).show();
                }

            }

        });


    }

    private void displaymedData(Medicine medicine) {
        String name = medicine.getMedname();
        String labo = medicine.getLabo();
        long mID = medicine.getMedid();

        String medData = "Name :" + name + "\nlabo :" + labo + "\nID :" + mID;
        Toast.makeText(this, medData, Toast.LENGTH_LONG).show();
    }

    private boolean isEmpty() {
        if ((TextUtils.isEmpty(firstname.getText().toString())) || (TextUtils.isEmpty(lastname.getText().toString())) ||
                (TextUtils.isEmpty(weight.getText().toString())) || (TextUtils.isEmpty(height.getText().toString())) ||
                (TextUtils.isEmpty(srfc.getText().toString())) || (TextUtils.isEmpty(poso.getText().toString())) ||
                (TextUtils.isEmpty(redu.getText().toString()))) {
            return true;
        } else {
            return false;
        }
    }


}
