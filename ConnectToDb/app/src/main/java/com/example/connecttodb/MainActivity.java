package com.example.connecttodb;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etRollNo, etCourse, etAverageMarks,deleteroll;
    private TextView tvResult;
    private DataBaseHelper dbHelper;
    public int check =0;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        etName=findViewById(R.id.etName);
        etRollNo=findViewById(R.id.etRollNo);
        etCourse=findViewById(R.id.etCourse);
        etAverageMarks=findViewById(R.id.etAverageMarks);
        deleteroll= findViewById(R.id.etDeleteByroll);
        Button btnInsert = findViewById(R.id.btnInsert);
        Button delete = findViewById(R.id.btnDelete);
        Button update=  findViewById(R.id.updatebtn);
        tvResult = findViewById(R.id.tvResult);
        dbHelper = new DataBaseHelper(this);
        btnInsert.setOnClickListener(v->{
           String name = etName.getText().toString();
           int roll = Integer.parseInt(etRollNo.getText().toString());
           String course = etCourse.getText().toString();
           float avgMarks = Float.parseFloat(etAverageMarks.getText().toString());
            boolean isinserted = dbHelper.insertStudent(name,roll,course,avgMarks);
            if(isinserted){
                tvResult.setText("inserted sucessfully ");
            }else{
                tvResult.setText("some thing wents wrongs");
            }
            fetchDataDisplay();
        });
        delete.setOnClickListener(v->{
            Boolean isdelete = dbHelper.DeleteData(Integer.parseInt(deleteroll.getText().toString()));
            if(isdelete){
                fetchDataDisplay();
            }
            else{
                Toast.makeText(MainActivity.this,"no item is match with your rollnumber ",Toast.LENGTH_LONG).show();
            }
        });
        update.setOnClickListener(v->{
            String name = etName.getText().toString();
            int roll = Integer.parseInt(etRollNo.getText().toString());
            String course = etCourse.getText().toString();
            float avgMarks = Float.parseFloat(etAverageMarks.getText().toString());
            Boolean isupdate = dbHelper.Updatevalue(name,course,avgMarks,roll);
            if(isupdate){
                fetchDataDisplay();
            }
            else{
                Toast.makeText(MainActivity.this,"no item is match with your rollnumber ",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void fetchDataDisplay() {
        Cursor cursor = dbHelper.getAllStudents();
        if (cursor.getCount() == 0) {
            tvResult.setText("no record found");
            return;
        }
        StringBuilder str = new StringBuilder();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") int rollNo = cursor.getInt(cursor.getColumnIndex("roll_no"));
            @SuppressLint("Range") String course = cursor.getString(cursor.getColumnIndex("course"));
            @SuppressLint("Range") float averageMarks = cursor.getFloat(cursor.getColumnIndex("average_marks"));
            str.append("Name: ").append(name).append("\n")
                    .append("Roll No: ").append(rollNo).append("\n")
                    .append("Course").append(course).append("\n")
                    .append("avragemarks").append(averageMarks).append("\n");
        }
        cursor.close();
        tvResult.setText(str.toString());
    }
}