package com.vara.platform.HelperMethods;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VaraDbHelperSql extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "vara.db";
    private static final String TABLE_NAME = "UserInfo";
    private static final String UserInfoId = "_id";
    private static final String Email = "email";


    private static final int DATABASE_VERSION = 1;
    private Context context;

    public VaraDbHelperSql(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Message.message(context, "Started....");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_USER_TABLE =

                "CREATE TABLE " + TABLE_NAME + " (" +

                        UserInfoId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        Email + " TEXT NOT NULL );";

            try {
                db.execSQL(SQL_CREATE_USER_TABLE);
                Message.message(this.context, "Table Created");
            } catch(Exception e) {
                Message.message(this.context, e.getMessage());
            }

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {

        } catch (Exception e) {

        }

    }
}
