package naveen.kumar.n01355935;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import io.paperdb.Paper;
import naveen.kumar.n01355935.model.CurrentUser;

public class MainActivity extends AppCompatActivity {


    Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Paper.init(MainActivity.this);

        login = findViewById(R.id.welcome_login_btn);
        signup = findViewById(R.id.welcome_signup_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        String phone = Paper.book().read(CurrentUser.userPhone);
        String password = Paper.book().read(CurrentUser.userPassword);

        if(phone != null && password != null){
            if(!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)){
                allowAccess(phone,password);
            }
        }


    }

    private void allowAccess(String phone, String password) {

    }
}