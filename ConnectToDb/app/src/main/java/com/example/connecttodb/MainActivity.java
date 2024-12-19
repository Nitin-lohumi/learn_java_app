package com.example.connecttodb;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    private MediaPlayer audioPlay;
    @SuppressLint("QueryPermissionsNeeded")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        final String username = "lohuminitin@gmail.com";
        final String password = "nfzq-keyu-eepa-okoy";

        // Set properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            // Create the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("lohuminitin082@gmail.com"));
            message.setSubject("Nitin app using email");
            message.setText("Body of the email is here");

            // Send the email
            Transport.send(message);
            Log.d("Email", "Email sent successfully!");
            Toast.makeText(this,"send message sucessfully",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"send message Unnsucessfully",Toast.LENGTH_LONG).show();
            Log.e("Email", "Error sending email: " + e.getMessage());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id==R.id.action_settings) {
            Toast.makeText(MainActivity.this, "you clicked on Setting section", Toast.LENGTH_LONG).show();
            return true;
        }
        if(id==R.id.action_about){
            Toast.makeText(MainActivity.this,"you clicked on about section",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    protected void onDestroy() {
        super.onDestroy();
        if (audioPlay != null) {
            audioPlay.release(); // Release resources
            audioPlay = null;
        }
    }
}