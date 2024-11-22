package com.example.app1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static  TextView filename;
    @SuppressLint("StaticFieldLeak")
    public static ImageView img;
    @SuppressLint({"MissingInflatedId", "StaticFieldLeak"})
    public static ProgressBar process;
    @SuppressLint("MissingInflatedId")
    public  Button btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        filename =findViewById(R.id.filename);
        img = findViewById(R.id.imageView);
        process = findViewById(R.id.progressBar);
        btn = findViewById(R.id.refreshButton);
        filename.setText("Image change using Api ");
        new FetchApi("https://coffee.alexflipnote.dev/random.json",true).execute();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchApi("https://coffee.alexflipnote.dev/random.json",true).execute();
            }
        });

    }
}