package lsvb.jogi.com.leichtspontanvokablbuch;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Created by jogi on 9/26/15.
 */
public class VokablDB {
    private SQLiteDatabase db;
    private final Context context;
    private final VokabelDbHelifer dbhelper;
    public VokablDB(Context c){
        context = c;
        dbhelper = new VokabelDbHelifer(context, VokablDbConstants.DATABASE_NAME, null,
                VokablDbConstants.DATABASE_VERSION);
    }
    public void close()
    {
        db.close();
    }
    public void open() throws SQLiteException
    {
        try {
            db = dbhelper.getWritableDatabase();
        }catch (SQLiteException ex){
            Log.v("Open db except caught",ex.getMessage());
            db = dbhelper.getReadableDatabase();
        }
    }
    public long insertWord(String Wort,String Ueberzetung, String Anwendung, long DateName)
    {
        try {
            ContentValues newWordValue =  new ContentValues();
            newWordValue.put(VokablDbConstants.DEUTSCH_WORT,Wort);
            newWordValue.put(VokablDbConstants.UEBERSETZUNG,Ueberzetung);
            newWordValue.put(VokablDbConstants.ANWENDENUG,Anwendung);
            newWordValue.put(VokablDbConstants.DATE_NAME,DateName);

            return  db.insert(VokablDbConstants.TABLE_NAME,null,newWordValue);
        }
        catch (SQLiteException ex)
        {
            Log.v("Insert Exception",ex.getMessage());
            return -1;
        }
    }
    public Cursor getWord()
    {
        Cursor c  = db.query(VokablDbConstants.TABLE_NAME,null,null,null,null,null,null);
        return  c;
    }
}
