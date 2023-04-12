package njust.dzh.fitnesssystem.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import njust.dzh.fitnesssystem.Bean.User;
import njust.dzh.fitnesssystem.DataBase.DataBaseHelper;



public class UserDao {
    private Context context;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    public UserDao(Context context) {
        this.context = context;
    }


    public void open() throws SQLiteException {
        dbHelper = new DataBaseHelper(context);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException exception) {
            db = dbHelper.getReadableDatabase();
        }
    }


    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }


    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("gender", user.getGender());
        values.put("nation", user.getNation());
        values.put("height", user.getHeight());
        values.put("weight", user.getWeight());
        db.insert("User", null, values);
    }



    public void modifyUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("gender", user.getGender());
        values.put("nation", user.getNation());
        values.put("height", user.getHeight());
        values.put("weight", user.getWeight());

        if (findUser(user) == true) {
            db.update("User", values, "id = ?", new String[]{user.getId()} );
        } else {
            values.put("id", "8888abc");
            db.insert("User", null, values);
        }
    }


    public boolean findUser(User user) {

        Cursor cursor = db.query("User", null, "id = ?", new String[]{user.getId()}, null, null, null);
        if (cursor == null || cursor.getCount() < 1) {
            return false;
        }
        cursor.close();
        return true;
    }


    public User getInformation(String sid) {
        Cursor cursor = db.query("User", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex("id"));
                if (id.equals(sid)) {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String age = cursor.getString(cursor.getColumnIndex("age"));
                    String gender = cursor.getString(cursor.getColumnIndex("gender"));
                    String nation = cursor.getString(cursor.getColumnIndex("nation"));
                    String height = cursor.getString(cursor.getColumnIndex("height"));
                    String weight = cursor.getString(cursor.getColumnIndex("weight"));
                    User user = new User(id, name, age, gender, nation, height, weight);
                    cursor.close();
                    return user;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return null;
    }


}