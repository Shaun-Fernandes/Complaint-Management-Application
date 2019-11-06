package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {


    EditText textEmail;
    EditText textPassword;
    EditText textConfirmPassword;
    Button buttonRegister;
    TextView textViewLogin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        getActionBar().setTitle("Complaint Management app");
//        getSupportActionBar().setTitle("Complaint Management app");  // provide compatibility to all the versions


        textEmail = findViewById(R.id.username);
        textPassword = findViewById(R.id.password);
        textConfirmPassword = findViewById(R.id.confirm_password);
        buttonRegister = findViewById(R.id.buttonRegister);
        textViewLogin = findViewById(R.id.linkLogin);
        firebaseAuth = FirebaseAuth.getInstance();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();
                String passwordConfirm = textConfirmPassword.getText().toString();
                if(email.isEmpty()){
                    textEmail.setError("Please enter an email ID!");
                    textEmail.requestFocus();
                }
                else if (password.isEmpty()){
                    textPassword.setError("Please enter a Password!");
                    textPassword.requestFocus();
                }
                else if (passwordConfirm.isEmpty()){
                    textConfirmPassword.setError("Please confirm your password!");
                    textConfirmPassword.requestFocus();
                }
                else if (!passwordConfirm.equals(password)){
                    textConfirmPassword.setError("Passwords do not match!");
                    textConfirmPassword.requestFocus();
                }
                else{
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Registering unsuccessful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registering Successful!", Toast.LENGTH_SHORT).show();
                                Intent ComplaintActivityIntent = new Intent(RegisterActivity.this, ComplaintDisplayActivity.class);
                                startActivity(ComplaintActivityIntent);
                            }
                        }
                    });
                }
            }
        });

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(LoginIntent);
            }
        });
    }
}
