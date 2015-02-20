package com.example.student.moneyexpense;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by student on 2/20/15 AD.
 */
public class DBhelper extends SQLiteOpenHelper{
    private static final String name = "courses.sqlite3";
    private static final int version = 2;


    public DBhelper(Context ctx) {
        super(ctx, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE course (" +
                "_id integer primary key autoincrement," +
                "amount text not null," +             // course code
                "date text not null," +           // credit
                "category text not null,);";            // letter grade e.g. A, B+
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS course;";
        db.execSQL(sql);
        this.onCreate(db);
    }
}
