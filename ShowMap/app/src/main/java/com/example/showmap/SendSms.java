package com.example.showmap;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SendSms extends AppCompatActivity{
    public EditText etMessage,etPhoneNumber ;
    public Button btnSend;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSendSMS);
        checkPermissionOfsms();
        }

    private void checkPermissionOfsms() {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            showPermissionExplanationDialog();
        }
        else{
            btnSend.setOnClickListener(v->{
                sendMsg();
            });
        }
    }

    private void showPermissionExplanationDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Go to setting -> permission and allow to SMS")
                .setPositiveButton("go", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                        SendSms sm =new SendSms();
                    }
                })
                .setNegativeButton("no no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(SendSms.this,MainActivity.class);
                        startActivity(intent);
                    }
                })
                .create()
                .show();
    }
    private void sendMsg() {
        String phonenumber  = etPhoneNumber.getText().toString();
        String msg = etMessage.getText().toString();
        if(phonenumber.isEmpty()||msg.isEmpty()){
            Toast.makeText(this,"please enter phone number or msg",Toast.LENGTH_SHORT).show();
        } else{
            try {
                SmsManager sm = SmsManager.getDefault();
                sm.sendTextMessage(phonenumber,null,msg,null,null);
                Toast.makeText(this, "SMS Sent!", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                 Toast.makeText(this, "Something Went Wrong!"+e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}


