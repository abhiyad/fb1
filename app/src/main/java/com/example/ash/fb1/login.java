package com.example.ash.fb1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static java.sql.Types.NULL;

public class login extends AppCompatActivity {

    private EditText editText;
    private EditText editText2;
    private Button button;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button)findViewById(R.id.button);
        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        tv=(TextView)findViewById(R.id.textView);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(this,MainActivity.class));
        }
        progressDialog=new ProgressDialog(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotosignup();
            }
        });


    }
    private void gotosignup()
    {
        Intent i = new Intent(this,signup.class);
        startActivity(i);
    }
    private void call()
    {
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
    }
    private void loginuser(){
        String email=editText.getText().toString().trim();
        String password=editText2.getText().toString().trim();
        //********************

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Enter email" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter password" , Toast.LENGTH_SHORT).show();
            return;
        }
        //************* all set
        progressDialog.setMessage("abhi ruko .......");
        progressDialog.show();

        //***************** all set again
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(login.this, "Ho gaya login",Toast.LENGTH_SHORT).show();
                            // now loaunch some activity
                            call();
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(login.this, "Nhi ho paya login",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
