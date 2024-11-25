package com.example.connecttodb;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studentDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ROLL_NO = "roll_no";
    private static final String COLUMN_COURSE = "course";
    private static final String COLUMN_AVERAGE_MARKS = "average_marks";
    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ROLL_NO + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_COURSE + " TEXT, " +
                COLUMN_AVERAGE_MARKS + " REAL)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertStudent(String name, int rollNo, String course, float averageMarks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_ROLL_NO, rollNo);
        values.put(COLUMN_COURSE, course);
        values.put(COLUMN_AVERAGE_MARKS, averageMarks);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }
    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM students", null);
    }
    public Boolean DeleteData(int rollnumber){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_NAME,"roll_no = ?",new String []{String.valueOf(rollnumber)});
        db.close();
        return res!=0;
    }
    public Boolean Updatevalue(String name,String course,Float averageMarks,int rollnumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content= new ContentValues();
        content.put("name",name);
        content.put("course", course);
        content.put("average_marks", averageMarks);
        int res = db.update(TABLE_NAME,content,"roll_no=?",new String[]{String.valueOf(rollnumber)});
        db.close();
        return  res>0;
    }
}
