package com.example.iliass.tp3_room;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SchoolDao {

    @Insert
    void insert(School school) ;

    @Update
    void update(School school) ;

    @Delete
    void delete(School school) ;


    @Query("DELETE FROM school_table")
    void deleteAllSchools() ;

    @Query("SELECT * FROM school_table ORDER BY priority DESC")
    LiveData<List<School>> getAllSchools() ;
}
