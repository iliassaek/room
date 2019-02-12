package com.example.iliass.tp3_room;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SchoolRepository {

    private SchoolDao schoolDao ;
    private LiveData<List<School>> allSchools ;

    public SchoolRepository(Application application){

        SchoolDatabase database = SchoolDatabase.getInstance(application) ;
        schoolDao = database.schoolDao() ;

        allSchools = schoolDao.getAllSchools() ;
    }


    public void insert(School school){
        new InsertSchoolAsyncTask(schoolDao).execute(school) ;
    }

    public void update(School school){
        new UpdateSchoolAsyncTask(schoolDao).execute(school) ;
    }

    public void delete(School school){
        new DeleteSchoolAsyncTask(schoolDao).execute(school) ;
    }

    public void deleteAllSchools(){
        new DeleteAllSchoolsAsyncTask(schoolDao).execute() ;
    }

    public LiveData<List<School>> getAllSchools(){
        return allSchools ;
    }


    private static class InsertSchoolAsyncTask extends AsyncTask<School, Void , Void>{
        private SchoolDao schoolDao ;

        private InsertSchoolAsyncTask(SchoolDao schoolDao){
            this.schoolDao = schoolDao ;
        }

        @Override
        protected Void doInBackground(School... schools) {

            schoolDao.insert(schools[0]);
            return null;
        }
    }



    private static class UpdateSchoolAsyncTask extends AsyncTask<School, Void , Void>{
        private SchoolDao schoolDao ;

        private UpdateSchoolAsyncTask(SchoolDao schoolDao){
            this.schoolDao = schoolDao ;
        }

        @Override
        protected Void doInBackground(School... schools) {

            schoolDao.update(schools[0]);
            return null;
        }
    }



    private static class DeleteSchoolAsyncTask extends AsyncTask<School, Void , Void>{
        private SchoolDao schoolDao ;

        private DeleteSchoolAsyncTask(SchoolDao schoolDao){
            this.schoolDao = schoolDao ;
        }

        @Override
        protected Void doInBackground(School... schools) {

            schoolDao.delete(schools[0]);
            return null;
        }
    }




    private static class DeleteAllSchoolsAsyncTask extends AsyncTask<Void, Void , Void>{
        private SchoolDao schoolDao ;

        private DeleteAllSchoolsAsyncTask(SchoolDao schoolDao){
            this.schoolDao = schoolDao ;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            schoolDao.deleteAllSchools();
            return null;
        }
    }
}
