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

public class LoginActivity extends AppCompatActivity {
    private EditText txtEmailLogin;
    private EditText txtpwd;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmailLogin =(EditText) findViewById(R.id.txtEmailLogin);
        txtpwd =(EditText) findViewById(R.id.txtPasswordLogin);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void btnUserLogin_Click(View v){
        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "please wait....", "Progressing..", true);
        (firebaseAuth.createUserWithEmailAndPassword(txtEmailLogin.getText().toString(), txtpwd.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                            i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                            startActivity(i);
                        }
                        else{
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }) ;



    }
}
