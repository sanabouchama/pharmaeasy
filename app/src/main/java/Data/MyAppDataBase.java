package Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import model.Patient;
import model.Preparation;
import model.Remainder;
import model.User;
import model.Medicine;

@Database(entities = {User.class,Medicine.class, Patient.class, Preparation.class,  Remainder.class},version =3,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class MyAppDataBase extends RoomDatabase {
  public static MyAppDataBase myDB=null;

   public abstract MyDao getDao();




   public static MyAppDataBase creatmydbInstance(Context context){

       if(myDB==null){
           myDB = Room.databaseBuilder(
                   context.getApplicationContext(),MyAppDataBase.class,
                   "mydb"
           )
                   .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                   .build();
       }
       return  myDB;
   }






}
