package lsvb.jogi.com.leichtspontanvokablbuch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jogi on 9/27/15.
 */
public class VokabelDbHelifer extends SQLiteOpenHelper{
    private static final String CREATE_TABLE="create table "+
            VokablDbConstants.TABLE_NAME+" ("+
            VokablDbConstants.KEY_ID+" integer primary key autoincrement, "+
            VokablDbConstants.DEUTSCH_WORT+" text not null, "+
            VokablDbConstants.UEBERSETZUNG+" text not null, "+
            VokablDbConstants.ANWENDENUG+" text not null, "+
            VokablDbConstants.DATE_NAME+" long);";

    public VokabelDbHelifer(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("VDbHelifer onCreate","Creating all the tables");
        try {
            db.execSQL(CREATE_TABLE);
        }catch (SQLiteException ex){
            Log.v("Create table exception",ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("TaskDbAdapter","Upgrading from version "+oldVersion+" to "+ newVersion+", which will destroy all old data");
        db.execSQL("drop table if exists "+VokablDbConstants.TABLE_NAME);
        onCreate(db);

    }
}
