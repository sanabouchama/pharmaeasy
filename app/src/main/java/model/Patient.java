package model;


import androidx.annotation.NonNull;

import androidx.room.Entity;

import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class Patient implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private  long protocol_code;
    private  String FirstName;
    private  String LastName;
    private  double height;
    private  double weight;
   private float bodysurface;
    private float dosage;

    public long getProtocol_code() {
        return protocol_code;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public float getBodysurface() {
        return bodysurface;
    }

    public float getDosage() {
        return dosage;
    }

    public void setProtocol_code(long protocol_code) {
        this.protocol_code = protocol_code;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setBodysurface(float bodysurface) {
        this.bodysurface = bodysurface;
    }

    public void setDosage(float dosage) {
        this.dosage = dosage;
    }



    public Patient(String FirstName, String LastName, double height, double weight, float bodysurface, float dosage) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.height = height;
        this.weight = weight;
        this.bodysurface = bodysurface;
        this.dosage = dosage;
    }
}
