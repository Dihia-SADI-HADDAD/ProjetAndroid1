package com.example.dihia.firebaseregistrationlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RegistrationActivity extends AppCompatActivity {
    private EditText txtEmailAdresse;
    private EditText txtPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        txtEmailAdresse = (EditText) findViewById(R.id.txtEmailRegistration);
        txtPassword = (EditText) findViewById(R.id.txtPasswordRegistration);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void btnRegistrationUser_Click(View v){

        final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this, "please wait...", "Progressing...",true);
        (firebaseAuth.createUserWithEmailAndPassword(txtEmailAdresse.getText().toString(), txtPassword.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              progressDialog.dismiss();

                if(task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else{
                    Log.e("ERROR", task.getException().toString());
                    Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
