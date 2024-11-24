package com.example.connecttodb;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private boolean isNightMode = false;
    public Spinner sp;
    public ArrayList<String> items = new ArrayList<>();
    public List<CheckBox> checkboxes = new ArrayList<>();
    public ArrayList<String> res =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button btnClickMe = findViewById(R.id.btn_click_me);
        sp  = findViewById(R.id.spinner);
        items.add("java");
        items.add("c++");
        items.add("python");
        items.add("javascript");
        items.add("c");
        items.add("php");
        items.add("others");
        ArrayAdapter<String> arr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        sp.setAdapter(arr);
        TextView tres1 = findViewById(R.id.textView2);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView tres2 =findViewById(R.id.textView5);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView tres3 = findViewById(R.id.textView6);
        Button btn = findViewById(R.id.button);
        checkboxes.add(findViewById(R.id.checkBox4));
        checkboxes.add(findViewById(R.id.checkBox3));
        checkboxes.add(findViewById(R.id.checkBox2));
        checkboxes.add(findViewById(R.id.checkBox));
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        CompoundButton.OnCheckedChangeListener Listener = (btnview,ischecked)->{
            String str = btnview.getText().toString();
            if (ischecked) {
                res.add(str);
            } else {
                res.remove(str);
            }
        };
        for (CheckBox checkBox:checkboxes) {
            checkBox.setOnCheckedChangeListener(Listener);
        }


        btn.setOnClickListener(v->{
            if (!res.isEmpty()) {
                String result = "Selected Options: " + String.join(", ", res);
                tres1.setText(result);
            } else {
                tres1.setText("No options selected.");
            }
            int SelectedRadio = radioGroup.getCheckedRadioButtonId();
            if(SelectedRadio!=-1){
                RadioButton radio = findViewById(SelectedRadio);
                tres3.setText(radio.getText().toString()+" ");
            }
            Toast.makeText(MainActivity.this,"lets see",Toast.LENGTH_SHORT).show();
             String selecteditem = sp.getSelectedItem().toString();
            tres2.setText(selecteditem);
        });





        int currentMode = AppCompatDelegate.getDefaultNightMode();
        isNightMode = (currentMode == AppCompatDelegate.MODE_NIGHT_YES);
        if (isNightMode) {
            btnClickMe.setText("Click Me (Night)");
        } else {
            btnClickMe.setText("Click Me (Day)");
        }
        btnClickMe.setOnClickListener(v -> {
            if (isNightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                btnClickMe.setText("Click Me (Day)");
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                btnClickMe.setText("Click Me (Night)");
            }
            isNightMode = !isNightMode;
        });
    }
}