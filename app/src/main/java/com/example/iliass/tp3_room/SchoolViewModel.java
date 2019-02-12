package com.example.iliass.tp3_room;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SchoolViewModel extends AndroidViewModel {

    private SchoolRepository repository ;
    private LiveData<List<School>> allSchools ;

    public SchoolViewModel(@NonNull Application application) {
        super(application);
        repository = new SchoolRepository(application) ;
        allSchools = repository.getAllSchools() ;
    }


    public void insert(School school){
        repository.insert(school);
    }

    public void update(School school){
        repository.update(school);
    }

    public void delete(School school){
        repository.delete(school);
    }

    public void deleteAllSchools(){
        repository.deleteAllSchools();
    }

    public LiveData<List<School>> getAllSchools(){
        return allSchools ;
    }
}
