package com.example.app1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
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
        if(!isInternetAvalilable(this)){
            showDilogBox(this);
            return;
        }
        new FetchApi("https://coffee.alexflipnote.dev/random.json",true).execute();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isInternetAvalilable(view.getContext())){
                    showDilogBox(view.getContext());
                    return;
                }
                new FetchApi("https://coffee.alexflipnote.dev/random.json",true).execute();
            }
        });
    }

    private void showDilogBox(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("No Internet Connection");
        builder.setMessage("please open your internet connection");
        builder.setPositiveButton("setting", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent in  = new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
                context.startActivity(in);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
        builder.show();
    }

    private boolean isInternetAvalilable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo net = cm.getActiveNetworkInfo();
        return net!=null&& net.isConnected();
    }
}