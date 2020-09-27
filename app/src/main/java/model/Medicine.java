package model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import java.util.Date;

@Entity
public class Medicine implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private  long medid;

    private  String  medname;
    private String labo ;
    private String pres;
    private float ci;
    private float cmin ;
    private float cmax;
    private float price;
    private double vlm;
    private String stab;



    public long getMedid() {
        return medid;
    }

    public String getMedname() {
        return medname;
    }

    public String getLabo() {
        return labo;
    }

    public String getPres() {
        return pres;
    }

    public float getCi() {
        return ci;
    }

    public float getCmin() {
        return cmin;
    }

    public float getCmax() {
        return cmax;
    }

    public float getPrice() {
        return price;
    }

    public double getVlm() {
        return vlm;
    }

    public String getStab() {
        return stab;
    }












    public void setMedid(long medid) {
        this.medid = medid;
    }

    public void setMedname(String medname) {
        this.medname = medname;
    }

    public void setLabo(String labo) {
        this.labo = labo;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public void setCi(float ci) {
        this.ci = ci;
    }

    public void setCmin(float cmin) {
        this.cmin = cmin;
    }

    public void setCmax(float cmax) {
        this.cmax = cmax;
    }

    public void setPrice(float price) { this.price = price; }

    public void setvlm(double vlm) {
        this.vlm = vlm;
    }

    public void setStab( String stab) {
        this.stab = stab;
    }







    public Medicine( String medname, String labo, String pres, float ci, float cmin, float cmax, float price,double vlm,String stab) {
        this.medname = medname;
        this.labo = labo;
        this.pres = pres;
        this.ci = ci;
        this.cmin = cmin;
        this.cmax = cmax;
        this.price = price;
        this.vlm = vlm;

        this.stab=stab;

    }

    public String toString() {
      String mednamelabo = medname+"/"+labo;
        return mednamelabo;
    }
}


