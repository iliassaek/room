package com.example.iliass.tp3_room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "school_table")
public class School {

    //Private attributes
    @PrimaryKey(autoGenerate = true)
    private int id ;

    private String title ;

    private String description ;

    private int priority ;

    //constructor
    public School(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    //id setter
    public void setId(int id) {
        this.id = id;
    }



    //all attributes getter
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }




}
