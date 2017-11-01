package com.cjt.sqldemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * =====================================
 * 作    者: 陈嘉桐
 * 版    本：1.1.4
 * 创建日期：2017/10/24
 * 描    述：
 * =====================================
 */
public class DBHelp extends SQLiteOpenHelper {
    public static final String CREATE_BOOK = "create table book (" +
            "id integer primary key autoincrement, " +
            "author text, " +
            "price real," +
            "pages integer, " +
            "name text)";

    Context context;

    public DBHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(context, "create successed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "onUpgrade oldVersion：" + oldVersion + " newVersion:" + newVersion, Toast.LENGTH_SHORT).show();
    }
}
