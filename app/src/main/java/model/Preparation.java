package model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


import java.io.Serializable;
import java.util.Date;


@Entity( indices = { @Index( value = { "mId" }), @Index( value = { "patId" })},
        foreignKeys = {
                @ForeignKey(entity = Patient.class,
                        parentColumns = "protocol_code",
                        childColumns = "patId"
                        ),
        @ForeignKey(entity = Medicine.class,
                parentColumns = "medid",
                childColumns = "mId",
            onDelete=ForeignKey.CASCADE),

})
public class Preparation implements Serializable  {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long pId;
    public   long mId;
    private  long patId;
    private  Date date ;
    private  float daa;
    private  float vaa ;
    private  float reduction;

    public long getpId() {
        return pId;
    }

    public long getmId() {
        return mId;
    }

    public long getPatId() {
        return patId;
    }

    public Date getDate() {
        return date;
    }

    public float getDaa() {
        return daa;
    }

    public float getVaa() {
        return vaa;
    }

    public float getReduction() {
        return reduction;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public void setPatId(long patId) {
        this.patId = patId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDaa(float daa) {
        this.daa = daa;
    }

    public void setVaa(float vaa) {
        this.vaa = vaa;
    }

    public void setReduction(float reduction) {
        this.reduction = reduction;
    }

    public Preparation(long mId, long patId, Date date, float daa, float vaa, float reduction) {
        this.mId = mId;
        this.patId = patId;
        this.date = date;
        this.daa = daa;
        this.vaa = vaa;
        this.reduction = reduction;
    }
}
