package org.maktab.tamrinshaduing.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import org.maktab.tamrinshaduing.moodel.Data;

@Database(entities = Data.class, version = 1, exportSchema = false)
@TypeConverters(RoomConverters.class)
public abstract class DataBase extends RoomDatabase {
    public abstract Dao getDao();

    public static DataBase getDataBase(Context context) {
        return Room.databaseBuilder(context, DataBase.class, "data.db").build();
    }
}
