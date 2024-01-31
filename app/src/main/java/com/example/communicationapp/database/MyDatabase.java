package com.example.communicationapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {PositionData.class}, version = 2, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract PositionDao positionDao();
}