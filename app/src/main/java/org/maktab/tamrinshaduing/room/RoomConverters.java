package org.maktab.tamrinshaduing.room;

import androidx.room.TypeConverter;

import java.util.Date;

public class RoomConverters {
    @TypeConverter
    public static Date fromeInte(Long timestampe) {
        Date date = new Date();
        date.setTime(timestampe);
        return date;
    }

    @TypeConverter
    public static Long fromeDate(Date date) {
        return date.getTime();
    }
}
