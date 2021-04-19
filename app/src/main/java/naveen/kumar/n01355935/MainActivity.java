package naveen.kumar.n01355935;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;
import naveen.kumar.n01355935.model.Users;

public class MainActivity extends AppCompatActivity {


    Button login, signup;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Paper.init(MainActivity.this);

        login = findViewById(R.id.welcome_login_btn);
        signup = findViewById(R.id.welcome_signup_btn);
        progressDialog = new ProgressDialog(MainActivity.this);

        progressDialog.setTitle("Logging In");
        progressDialog.setMessage("Please wait, while we getting your account");
        progressDialog.setCanceledOnTouchOutside(false);

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


        String phone = Paper.book().read("currentUserPhone");
        String password = Paper.book().read("currentUserPassword");



        if(phone != null && password != null){
                progressDialog.show();
                allowAccess(phone,password);

        }


    }

    private void allowAccess(String phone, String password) {

        DatabaseReference root;
        root = FirebaseDatabase.getInstance().getReference();

        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Users").child(phone).exists()){
                    Users users = snapshot.child("Users").child(phone).getValue(Users.class);

                    if(users.getPhone().equals(phone)){
                        if(users.getPassword().equals(password)){
                            Toast.makeText(getApplicationContext(),"Welcome "+users.getName(),Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Please enter the password",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }else {
                        progressDialog.dismiss();
                    }

                }else {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}