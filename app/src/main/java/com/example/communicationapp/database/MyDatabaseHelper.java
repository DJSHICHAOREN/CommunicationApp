package com.example.communicationapp.database;

import android.content.Context;

import androidx.room.Room;

public class MyDatabaseHelper {

    private static MyDatabaseHelper mInstance;
    private PositionDao mPositionDao;
    public MyDatabaseHelper(Context context) {
        // 如果想在主线程中添加数据，也要使用allowMainThreadQueries()
        MyDatabase myDatabase = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "mydatabase.db")
                .build();
        mPositionDao = myDatabase.positionDao();
    }

    public static MyDatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyDatabaseHelper(context);
        }
        return mInstance;
    }

    public PositionDao getPositionDao() {
        return mPositionDao;
    }
}
