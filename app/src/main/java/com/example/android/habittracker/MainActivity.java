package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.habittracker.HabitContract.HabitEntry;


public class MainActivity extends AppCompatActivity {

    //With the help of Database helper we can access our database.
    private HabitDbHelper mDbHelper = new HabitDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = readHabitsDatabase();
        insertHabit();
        displayDatabaseInfo(cursor);
    }

    private Cursor readHabitsDatabase() {
        // Create/open a database to read.
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        //The projection selects the columns which will be used.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_HOBBY,
                HabitEntry.COLUMN_HABIT_MINUTES,
                HabitEntry.COLUMN_HABIT_DAY};

        // With cursor we can make a query.
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }


    //With this method we can display information of the current state of the database.
    private void displayDatabaseInfo(Cursor cursor) {

        TextView displayView = (TextView) findViewById(R.id.info_hobby_text_view);

        try {

            displayView.setText("The habits table contains " + cursor.getCount() + " habits.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_HOBBY + " - " +
                    HabitEntry.COLUMN_HABIT_MINUTES + " - " +
                    HabitEntry.COLUMN_HABIT_DAY + "\n");

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int sportColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_HOBBY);
            int hoursColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_MINUTES);
            int dayColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DAY);

            cursor.moveToFirst();

            int currentID = cursor.getInt(idColumnIndex);
            String currentHobby = cursor.getString(sportColumnIndex);
            int currentMinutes = cursor.getInt(hoursColumnIndex);
            String currentDay = cursor.getString(dayColumnIndex);

            displayView.append(("\n" + currentID + " - " +
                    currentHobby + " - " +
                    currentMinutes + " - " +
                    currentDay));
        } finally {

            cursor.close();
        }
    }


    //With this method we can insert data into our database.
    private void insertHabit() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_HOBBY, "Fishing");
        values.put(HabitEntry.COLUMN_HABIT_MINUTES, 90);
        values.put(HabitEntry.COLUMN_HABIT_DAY, "Saturday");

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, "Error occurred when saving the habit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Habit successfully saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
}
