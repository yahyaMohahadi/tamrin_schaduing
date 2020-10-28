package org.maktab.tamrinshaduing.room;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.maktab.tamrinshaduing.moodel.Data;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM data")
    LiveData<List<Data>> getAll();

    @Insert
    void insert(Data data);

    @Delete
    void delete(Data data);

    @Update
    void update(Data data);

}