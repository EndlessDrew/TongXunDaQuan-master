package feicui.projectone.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by z on 2016/9/2.
 */
public class MyOpenHelper  extends SQLiteOpenHelper{
    //数据库版本
    public static final int DATABASE_VERSION = 1;
    //数据库名称
    public static final String DATABASE_NAME = "phone.db";


    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表
        db.execSQL(TypeEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //删除原表
        db.execSQL(TypeEntry.SQL_DELETE_TABLE);
        //建表
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}