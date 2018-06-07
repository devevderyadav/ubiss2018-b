package com.ubiss2018.dev.ubiss2018;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikes on 21.3.2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "MeditationDb";
    private static final String TABLE_NAME = "Meditation";
    private static final String KEY_ID = "id";
    private static final String MOOD_START = "mood_start";
    private static final String LOCATION_START = "location_start";
    private static final String MEDITATION_DURATION = "meditation_duration";
    private static final String SOUND = "sound";
    private static final String GUIDANCE = "guidance";
    private static final String MOOD_END = "mood_end";
    private static final String HR = "hr";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Meditation ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, mood_start STRING, location_start STRING, meditation_duration STRING, sound " +
                "STRING, guidance STRING, mood_end STRING, hr STRING )";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void addMeditationSession(MeditationSession meditationsession) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOOD_START, meditationsession.getMoodStart());
        values.put(LOCATION_START, meditationsession.getLocationStart());
        values.put(MEDITATION_DURATION, meditationsession.getDuration());
        values.put(SOUND, meditationsession.getSound());
        values.put(GUIDANCE, meditationsession.getGuidance());
        values.put(MOOD_END, meditationsession.getMoodEnd());
        values.put(HR, meditationsession.getHr());

        db.insert(TABLE_NAME, null, values);
        db.close();

        Log.i("DatabaseHandler", "Added meditation");
    }

    public List<MeditationSession> getMeditationSessions() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<MeditationSession> meditationList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY id";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // this is quick fix for bug which crashes if db is empty
        if (cursor.getCount() == 0) {
            return null;
        }

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                try {

                    String m_id = String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_ID)));
                    String mood_start = cursor.getString(cursor.getColumnIndex(MOOD_START));
                    String location_start = cursor.getString(cursor.getColumnIndex(LOCATION_START));
                    String meditation_duration = cursor.getString(cursor.getColumnIndex(MEDITATION_DURATION));
                    String sound = cursor.getString(cursor.getColumnIndex(SOUND));
                    String guidance = cursor.getString(cursor.getColumnIndex(GUIDANCE));
                    String mood_end = cursor.getString(cursor.getColumnIndex(MOOD_END));
                    String hr = cursor.getString(cursor.getColumnIndex(HR));
                    meditationList.add(new MeditationSession(m_id,mood_start, location_start, meditation_duration,
                            sound, guidance, mood_end, hr));
                    cursor.moveToNext();

                    Log.i("DatabaseHandler", "KEY_ID " + m_id);
                    Log.i("DatabaseHandler", "MOOD_START " + mood_start);
                    Log.i("DatabaseHandler", "LOCATION_START " + location_start);
                    Log.i("DatabaseHandler", "MEDITATION_DURATION " + meditation_duration);
                    Log.i("DatabaseHandler", "SOUND " + sound);
                    Log.i("DatabaseHandler", "GUIDANCE " + guidance);
                    Log.i("DatabaseHandler", "MOOD_END " + mood_end);
                    Log.i("DatabaseHandler", "HR " + hr);

                } catch (Exception e) {
                }


            }
        }

        db.close();

        return meditationList;
    }
}