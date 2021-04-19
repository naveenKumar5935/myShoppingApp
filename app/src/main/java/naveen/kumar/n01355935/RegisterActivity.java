package naveen.kumar.n01355935;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {


    EditText nameET, phoneET, passwordET;
    Button registerButton;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        nameET = findViewById(R.id.register_name_et);
        phoneET = findViewById(R.id.register_phone_et);
        passwordET = findViewById(R.id.register_password_et);
        registerButton = findViewById(R.id.register_signup_btn);
        progressDialog = new ProgressDialog(RegisterActivity.this);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(v);
            }
        });


    }

    private void createAccount(View v) {
        String name = nameET.getText().toString();
        String phone = phoneET.getText().toString();
        String password = passwordET.getText().toString();


        if(TextUtils.isEmpty(name)){
            Snackbar.make(v,"Please enter name",Snackbar.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(phone)){
            Snackbar.make(v,"Please enter phone No.",Snackbar.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(password)) {
            Snackbar.make(v, "Please enter password", Snackbar.LENGTH_LONG).show();
        }else {

            progressDialog.setTitle("Create Account");
            progressDialog.setMessage("Please wait, while we creating your account");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            signingUp(name,phone,password);
        }
        


    }

    private void signingUp(String name, String phone, String password) {
        DatabaseReference root;
        root = FirebaseDatabase.getInstance().getReference();

        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child("Users").child(phone).exists()){
                    Toast.makeText(getApplicationContext(),"User already exists",Toast.LENGTH_LONG).show();
                }
                else {
                    HashMap<String,Object> userdata = new HashMap<>();
                    userdata.put("name",name);
                    userdata.put("phone",phone);
                    userdata.put("password",password);

                    root.child("Users").child(phone).updateChildren(userdata)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                           if(task.isSuccessful()){
                               progressDialog.dismiss();
                               Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                               startActivity(intent);
                           }
                           else {
                               progressDialog.dismiss();
                               Toast.makeText(getApplicationContext(),"Network error",Toast.LENGTH_LONG).show();

                           }

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}