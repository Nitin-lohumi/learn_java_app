package  com.example.showmap;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class LoginActivity extends AppCompatActivity {

    Button loginbt;
    TextView Error1,Error2,newuser;
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

    }
}
