package com.example.apprecettes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class Register extends AppCompatActivity {
    private EditText editTextEmail , editTextPassword , editTextConfirmPassword;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        Toast.makeText(Register.this, "You can register now.", Toast.LENGTH_LONG).show();

        editTextEmail = findViewById(R.id.editText_register_email);
        editTextPassword = findViewById(R.id.editText_register_password);
        editTextConfirmPassword = findViewById(R.id.editText_register_confirm_password);
        progressBar = findViewById(R.id.progressBar);

        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textEmail = editTextEmail.getText().toString();
                String textPWD = editTextPassword.getText().toString();
                String textConfirmPWD = editTextConfirmPassword.getText().toString();

                if(TextUtils.isEmpty(textEmail)){
                    Toast.makeText(Register.this, "Please enter your email",Toast.LENGTH_LONG).show();
                    editTextEmail.setError("Email is required");
                    editTextEmail.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()){
                    Toast.makeText(Register.this, "Please re-enter your email",Toast.LENGTH_LONG).show();
                    editTextEmail.setError("Valid email is required");
                    editTextEmail.requestFocus();
                }
                else if (TextUtils.isEmpty(textPWD)){
                    Toast.makeText(Register.this, "Please enter your password",Toast.LENGTH_LONG).show();
                    editTextPassword.setError("Password is required");
                    editTextPassword.requestFocus();
                }
                else if (textPWD.length() < 6 ){
                    Toast.makeText(Register.this, "Password should be at least 6 digits.",Toast.LENGTH_LONG).show();
                    editTextPassword.setError("Password to weak");
                    editTextPassword.requestFocus();
                }
                else if (TextUtils.isEmpty(textConfirmPWD)){
                    Toast.makeText(Register.this, "Please enter your confirm password",Toast.LENGTH_LONG).show();
                    editTextConfirmPassword.setError("Confirm Password is required");
                    editTextConfirmPassword.requestFocus();
                }
                else if (!textPWD.equals(textConfirmPWD)){
                    Toast.makeText(Register.this, "Please same password",Toast.LENGTH_LONG).show();
                    editTextConfirmPassword.setError("Password confirmation is required");
                    editTextConfirmPassword.requestFocus();

                    editTextPassword.clearComposingText();
                    editTextConfirmPassword.clearComposingText();
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    registerUser(textEmail,textPWD);
                }
            }
        });
    }

    private void registerUser(String textEmail, String textPWD) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textEmail, textPWD).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "User Register",Toast.LENGTH_LONG).show();
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    firebaseUser.sendEmailVerification();
                    progressBar.setVisibility(View.GONE);
                    /*
                    Intent intent = new Intent(RegisterActivity.this, userProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivities(new Intent[]{intent});
                    finish();

                     */


                }
            }
        });
    }
    }
