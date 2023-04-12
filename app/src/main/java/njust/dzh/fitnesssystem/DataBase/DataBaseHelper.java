package njust.dzh.fitnesssystem.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "fitness.db";

    public static final int VERSION = 1;

    public static final String CREATE_USER = "create table User ("
            + "id text primary key,"
            + "name text,"
            + "age text,"
            + "gender text,"
            + "nation text,"
            + "height text,"
            + "weight text )";

    public DataBaseHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
