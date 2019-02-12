package com.example.iliass.tp3_room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {School.class} , version = 1)
public abstract class SchoolDatabase extends RoomDatabase {
    private static SchoolDatabase instance ;

    public abstract  SchoolDao schoolDao() ;


    public static synchronized SchoolDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SchoolDatabase.class, "school_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static  RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAscyncTask(instance).execute() ;
        }
    };


    private static class PopulateDbAscyncTask extends AsyncTask<Void, Void , Void>{
        private SchoolDao schoolDao ;

        private PopulateDbAscyncTask(SchoolDatabase db){
            schoolDao = db.schoolDao() ;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            schoolDao.insert(new School("Title1" , "Description1" , 1));
            schoolDao.insert(new School("Title2" , "Description2" , 2));
            schoolDao.insert(new School("Title3" , "Description3" , 3));


            return null;
        }
    }

}
