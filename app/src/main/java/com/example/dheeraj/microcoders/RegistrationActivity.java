package com.example.dheeraj.microcoders;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName,userEmail,userPassword;
    private Button Regiter;
    private TextView LoginID;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setUIViews();

        firebaseAuth=FirebaseAuth.getInstance();
        Regiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                    String useremail=userEmail.getText().toString().trim();
                    String password=userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(useremail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                           Toast.makeText(RegistrationActivity.this,"registration Successful",Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                        }
                        else
                            {
                                Toast.makeText(RegistrationActivity.this,"registration UnSuccessful",Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }
            }
        });

        LoginID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });

    }



    private void setUIViews()
    {
        userName=(EditText)findViewById(R.id.etUserName);
        userEmail=(EditText)findViewById(R.id.etEmailID);
        userPassword=(EditText)findViewById(R.id.etPassword);
        Regiter=(Button)findViewById(R.id.btnRegister);
        LoginID=(TextView)findViewById(R.id.tvSignIn);

    }

    private Boolean validate()
    {
        Boolean result=false;

        String name =userName.getText().toString();
        String email=userEmail.getText().toString();
        String password=userPassword.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this,"Please enter your details properly ",Toast.LENGTH_LONG).show();

        }
        else
        {
            result=true;

        }
        return result;

    }
}
