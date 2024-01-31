package com.example.communicationapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PositionDao {

    @Insert
    long insert(PositionData position);

    @Update
    void update(PositionData position);

    @Delete
    void delete(PositionData position);

    @Query("SELECT * FROM position_data")
    List<PositionData> selectAll();
}
