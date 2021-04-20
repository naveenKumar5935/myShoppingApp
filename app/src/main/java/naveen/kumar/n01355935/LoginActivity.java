package naveen.kumar.n01355935;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;
import naveen.kumar.n01355935.model.Users;

public class LoginActivity extends AppCompatActivity {

    EditText phoneET, passwordET;
    Button loginButton;
    ProgressDialog progressDialog;
    CheckBox rememberMe;
    TextView adminPanel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneET = findViewById(R.id.login_phone_et);
        passwordET = findViewById(R.id.login_password_et);
        loginButton = findViewById(R.id.login_login_btn);
        adminPanel = findViewById(R.id.login_adminButtonTv);
        progressDialog = new ProgressDialog(LoginActivity.this);
        rememberMe = findViewById(R.id.login_checkbox);
        Paper.init(LoginActivity.this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                loginAccount(v);
            }
        });


        adminPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("Admin Login");
                adminPanel.setVisibility(View.INVISIBLE);

                loginAccount(v);

            }
        });



    }

    private void loginAccount(View v) {
        String phone = phoneET.getText().toString();
        String password = passwordET.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Snackbar.make(v,"Please enter phone No.",Snackbar.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(password)) {
            Snackbar.make(v, "Please enter password", Snackbar.LENGTH_LONG).show();
        }else {

            progressDialog.setTitle("Logging In");
            progressDialog.setMessage("Please wait, while we getting your account");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            if(loginButton.getText().toString().matches("Admin Login")){
                loginAdmin(phone,password);
            }
            else {
                loginUser(phone,password);
            }


        }




    }

    private void loginAdmin(String phone, String password) {

        if(rememberMe.isChecked()){
            Paper.book().write("currentUserPhone",phone);
            Paper.book().write("currentUserPassword",password);

        }
        DatabaseReference root;
        root = FirebaseDatabase.getInstance().getReference();

        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("admins").exists()){
                    Users users = snapshot.child("admins").getValue(Users.class);

                    if(users.getPhone().equals(phone)){
                        if(users.getPassword().equals(password)){
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Wrong password",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Wrong credentials",Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"User does not exists",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void loginUser(String phone, String password) {

        if(rememberMe.isChecked()){
            Paper.book().write("currentUserPhone",phone);
            Paper.book().write("currentUserPassword",password);

        }

        DatabaseReference root;
        root = FirebaseDatabase.getInstance().getReference();

        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Users").child(phone).exists()){
                    Users users = snapshot.child("Users").child(phone).getValue(Users.class);

                    if(users.getPhone().equals(phone)){
                        if(users.getPassword().equals(password)){
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Wrong password",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }

                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"User does not exists",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}