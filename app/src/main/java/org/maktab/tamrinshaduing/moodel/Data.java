package org.maktab.tamrinshaduing.moodel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "data")
public class Data {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "wifi")
    private boolean mBooleanWifi;

    @NonNull
    @ColumnInfo(name = "name")
    private String mStringName;

    @NonNull
    @ColumnInfo(name = "date")
    private Date mDate;


    @NonNull
    public Date getDate() {
        return mDate;
    }

    public void setDate(@NonNull Date date) {
        mDate = date;
    }

    public Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBooleanWifi() {
        return mBooleanWifi;
    }

    public void setBooleanWifi(boolean booleanWifi) {
        mBooleanWifi = booleanWifi;
    }

    @NonNull
    public String getStringName() {
        return mStringName;
    }

    public void setStringName(@NonNull String stringName) {
        mStringName = stringName;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", mBooleanWifi=" + mBooleanWifi +
                ", mStringName='" + mStringName + '\'' +
                ", mDate=" + mDate +
                '}';
    }
}
