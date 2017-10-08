package com.example.android.habittracker;

import android.provider.BaseColumns;

/**
 * Created by Adam Cadmon on 2017. 06. 18.
 */

public class HabitContract {

    // This is an empty constructor.
    private HabitContract() {
    }

    //Here we can define the constant values of the habits database table.
    public static final class HabitEntry implements BaseColumns {


        //Name of database table for hobbies.
        public final static String TABLE_NAME = "habits";


        //Unique ID number for the habit with INTEGER data type.
        public final static String _ID = BaseColumns._ID;


        //Name of the hobby with TEXT data type.
        public final static String COLUMN_HABIT_HOBBY = "hobby";


        // It represent how many minutes we spend with a certain hobby (INTEGER data type)
        public final static String COLUMN_HABIT_MINUTES = "minutes";


        //Day of the hobby with TEXT data type.
        public final static String COLUMN_HABIT_DAY = "day";
    }
}
