package com.example.apprecettes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private EditText editTextLoginEmail, editTextLoginPassword;
    private ProgressBar progressBarLogin;

    private FirebaseAuth authProfile;
    private static final String TAG = "Login";

    public Login() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextLoginPassword = findViewById(R.id.editText_login_password);
        editTextLoginEmail = findViewById(R.id.editText_login_email);
        progressBarLogin = findViewById(R.id.progressBarLogin);
        authProfile = FirebaseAuth.getInstance();

        Button loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TextEmail = editTextLoginEmail.getText().toString();
                String TExtPWD = editTextLoginPassword.getText().toString();


                if (TextUtils.isEmpty(TextEmail)) {
                    Toast.makeText(Login.this, "Please enter your email", Toast.LENGTH_LONG).show();
                    editTextLoginEmail.setError("Email is required");
                    editTextLoginEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(TextEmail).matches()) {
                    Toast.makeText(Login.this, "Please re-enter your email", Toast.LENGTH_LONG).show();
                    editTextLoginEmail.setError("Valid email is required");
                    editTextLoginEmail.requestFocus();
                } else if (TextUtils.isEmpty(TExtPWD)) {
                    Toast.makeText(Login.this, "Please enter your password", Toast.LENGTH_LONG).show();
                    editTextLoginPassword.setError("Password is required");
                    editTextLoginPassword.requestFocus();
                } else if (TExtPWD.length() < 6) {
                    Toast.makeText(Login.this, "Password should be at least 6 digits.", Toast.LENGTH_LONG).show();
                    editTextLoginPassword.setError("Password to weak");
                    editTextLoginPassword.requestFocus();
                } else {
                    progressBarLogin.setVisibility(View.VISIBLE);
                    loginUser(TextEmail, TExtPWD);
                }
            }
        });
    }

    private void loginUser(String email, String password) {
        authProfile.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Your are logged", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, home.class);
                    startActivity(intent);
                } else {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        editTextLoginEmail.setError("User does not exit or is no longer valid. Please register again");
                        editTextLoginEmail.requestFocus();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        editTextLoginEmail.setError("Invalid credentials. check and re-enter");
                        editTextLoginEmail.requestFocus();

                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage(),e);
                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
                progressBarLogin.setVisibility(View.GONE);
            }
        });
    }
}