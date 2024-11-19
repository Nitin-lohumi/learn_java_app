package com.example.app1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//ListView list;
 Spinner sp;
 AutoCompleteTextView tx;
TextView listText;
ArrayList<String> arr = new ArrayList<>();
ArrayList<String> str = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        sp = findViewById(R.id.spinner);
        tx=  findViewById(R.id.autoCompleteTextView);
        listText = findViewById(R.id.textView2);
        listText.setText("Select the item");
        arr.add("nitin");
        arr.add("shubham");
        arr.add("nirmal");
        arr.add("mayank");
        arr.add("pooja");
        arr.add("nitin");
        arr.add("shubham");
        arr.add("nirmal");
        arr.add("mayank");
        arr.add("pooja");
        arr.add("nitin");
        arr.add("shubham");
        arr.add("nirmal");
        arr.add("mayank");
        arr.add("pooja");
        arr.add("nitin");
        arr.add("shubham");
        arr.add("nirmal");
        arr.add("mayank");
        arr.add("pooja");
        ArrayAdapter<String> adapter  = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arr);
        sp.setAdapter(adapter);
    //         Auto complete view
        str.add("java");
        str.add("c++");
        str.add("javascript");
        str.add("python");
        str.add("c");
        str.add("React");
        str.add("express");
        str.add("kotlin");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,str);
        tx.setAdapter(adapter1);
        tx.setThreshold(1);
    }
}