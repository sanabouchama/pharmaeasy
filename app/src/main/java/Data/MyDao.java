package Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import model.Medicine;
import model.Patient;
import model.Preparation;
import model.Remainder;
import model.User;

@Dao
public interface MyDao {


    @Insert
    void addUser(User user);

    @Query(" SELECT * FROM User WHERE password = :password")
    User getUser(String password);


    @Insert
    long addmed(Medicine medicine);



    @Query(" SELECT * FROM REMAINDER WHERE medId = :mid")
    Remainder getrema(long mid);



    @Delete
    void delete(Medicine medicine);

    @Query("SELECT * FROM medicine")
    List<Medicine> getAllmed();

    @Query("UPDATE Medicine SET medname = :mmedname WHERE medid = :mID")
    void updatename(long mID, String mmedname);

    @Query("UPDATE Medicine SET labo = :mmedlabo WHERE medid = :mID")
    void updatlabo(long mID, String mmedlabo);

    @Query("UPDATE Medicine SET pres = :mmedpre WHERE medid = :mID")
    void updatpres(long mID, String mmedpre);

    @Query("UPDATE Medicine SET ci = :mmedci WHERE medid = :mID")
    void updateci(long mID, String mmedci);

    @Query("UPDATE Medicine SET cmin = :mmedcmin WHERE medid = :mID")
    void updatecmin(long mID, String mmedcmin);

    @Query("UPDATE Medicine SET price = :mmedprice WHERE medid = :mID")
    void updateprice(long mID, String mmedprice);

    @Query("UPDATE Medicine SET cmax = :mmedcmax WHERE medid = :mID")
    void updatecmax(long mID, String mmedcmax);

    @Query("UPDATE Medicine SET vlm = :mmedvlm WHERE medid = :mID")
    void updatevlm(long mID, String mmedvlm);

    @Query("UPDATE Medicine SET stab = :mmedstab WHERE medid = :mID")
    void updatestab(long mID, String mmedstab);

    @Query("UPDATE Remainder SET remainder = :remainderr WHERE idreli = :mID")
    void updateremaindeer(long mID, Double remainderr);

    @Query("UPDATE Remainder SET remainderdate = :remainderr WHERE idreli = :rID")
    void updateremaindeerdate(long rID, Date remainderr);

    @Query("UPDATE Remainder SET remainderperim = :remainderr WHERE medid = :rID")
    void updateremainderdateperim(long rID, Date remainderr);


    @Query(" SELECT * FROM Medicine WHERE medname = :medname")
    Medicine getMed(String medname);

    @Insert
    long addpatient(Patient patient);

    @Insert
    long addparepa(Preparation preparation);

    @Query(" SELECT FirstName FROM Patient WHERE protocol_code = :pID")
    String nompat(long pID);

    @Query(" SELECT LastName FROM Patient WHERE protocol_code = :pID")
    String prepat(long pID);

    @Query(" SELECT  medname FROM Medicine WHERE medid = :pID")
    String namemed(long pID);


    @Query(" SELECT * FROM Patient WHERE protocol_code = :pid")
    Patient getpatt(long pid);


    @Query("SELECT * FROM preparation")
    List<Preparation> getAllpreparation();

    @Query("SELECT * FROM patient")
    List<Patient> getAllpatient();

    @Query("UPDATE User SET password = :newpassword Where password= :oldpassword ")
    void updatepassword(String oldpassword, String newpassword);

    @Delete
    void resetmed(List<Medicine> medicines);

    @Delete
    void resetpatient(List<Patient> patients);

    @Delete
    void resetpreparation(List<Preparation> preparations);



    @Query("SELECT * FROM Remainder WHERE  remainderperim <=:date")
    List<Remainder> perimmed(Date date);

    @Insert
    void addrem(Remainder remainder);

    @Query("SELECT * FROM remainder")
    List<Remainder> getAllremainder();

    @Delete
    void resetremainder(List<Remainder> remainders);


    @Query(" SELECT * FROM Medicine WHERE medid = :mid")
    Medicine getMedr(long mid);

}

