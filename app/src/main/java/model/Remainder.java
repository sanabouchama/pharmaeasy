package model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;


@Entity( indices = { @Index( value = { "medId" })},
        foreignKeys = {
        @ForeignKey(entity = Medicine.class,
                parentColumns = "medid",
                childColumns = "medId",
                onDelete = ForeignKey.CASCADE
        )
})
public class Remainder implements Serializable {
@PrimaryKey(autoGenerate = true)
@NonNull
    private long idreli;
    private long medId;
    private Double remainder;
    private Date remainderdate;
    private  Date remainderperim;

    public void setIdreli(long idreli) {
        this.idreli = idreli;
    }

    public void setMedId(long medId) {
        this.medId = medId;
    }

    public void setRemainderdate(Date remainderdate) {
        this.remainderdate = remainderdate;
    }

    public void setRemainderperim(Date remainderperim) {
        this.remainderperim = remainderperim;
    }

    public long getIdreli() {
        return idreli;
    }

    public long getMedId() {
        return medId;
    }

    public Date getRemainderdate() {
        return remainderdate;
    }

    public Date getRemainderperim() {
        return remainderperim;
    }

    public void setRemainder(Double remainder) {
        this.remainder = remainder;
    }

    public Double getRemainder() {
        return remainder;
    }

    public Remainder(long medId, Double remainder, Date remainderdate, Date remainderperim) {
        this.medId = medId;
        this.remainder = remainder;
        this.remainderdate = remainderdate;
        this.remainderperim = remainderperim;
    }
}
