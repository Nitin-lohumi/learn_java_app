package  com.example.showmap;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class LoginActivity extends AppCompatActivity {

    Button loginbt;
    TextView Error1,Error2,newuser,db;
    EditText eName,ePassword;
    private DbHelper dbHelper;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbt = findViewById(R.id.loginBtn);
        Error1 = findViewById(R.id.Error1);
        Error2 = findViewById(R.id.Error2);
        eName = findViewById(R.id.Name);
        ePassword = findViewById(R.id.password);
        newuser = findViewById(R.id.new_user);
        dbHelper= new DbHelper(this);
        db = findViewById(R.id.db);
         loginbt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String name= eName.getText().toString();
                 String pass = ePassword.getText().toString();
                 boolean insert  = dbHelper.insert(name,pass);
                 if(insert){
                     DisplayData();
                     Toast.makeText(getApplicationContext(),"login sucessfull",Toast.LENGTH_LONG).show();
                 }else{
                     DisplayData();
                     Toast.makeText(getApplicationContext(),"login unsucessfull",Toast.LENGTH_LONG).show();
                 }
             }
         });
    }
    public void DisplayData(){
        Cursor cursor = dbHelper.FetchData();
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"no data to display",Toast.LENGTH_LONG).show();
            return;
        }

        StringBuilder storedata = new StringBuilder();
        while (cursor.moveToNext()){
            @SuppressLint("Range") String name=cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String pass = cursor.getString(cursor.getColumnIndex("password"));
            storedata.append("name: ").append(name).append("\n")
                    .append("pass: ").append(pass).append("\n");
        }
        cursor.close();
        db.setText(storedata.toString());
    }
}
