package com.example.ash.fb1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth fauth;
    private FirebaseDatabase mbase;
    private DatabaseReference msg1;
    private TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbase = FirebaseDatabase.getInstance();
        fauth = FirebaseAuth.getInstance();
        msg1 = mbase.getReference().child("messages");
        final EditText etext1=(EditText)findViewById(R.id.et1);
        final TextView tview1= (TextView)findViewById(R.id.tv1);
        logout=(TextView)findViewById(R.id.textView2);
        Button sendbtn = (Button) findViewById(R.id.sbtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dologout();
            }
        });
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Send messages on click
                String in=etext1.getText().toString();
                msg1.setValue(in);


                //msg1.setValue("Chala gya");

                // Clear input box
                //mMessageEditText.setText("");
            }
        });


                msg1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        //System.out.println(snapshot.getValue());
                        tview1.setText(snapshot.getValue(String.class));
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }
            private void dologout()
            {
                fauth.signOut();
                Intent i = new Intent(this,login.class);
                startActivity(i);
            }

        }


