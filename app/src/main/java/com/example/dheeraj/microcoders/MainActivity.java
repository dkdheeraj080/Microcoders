package com.example.dheeraj.microcoders;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText Username;
    private EditText password;
    private Button Login;
   // private TextView ForgetPassword;
    private TextView Signup;
    private int counter =3;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username=(EditText)findViewById(R.id.etName);
        password=(EditText)findViewById(R.id.etPassword);
        Login=(Button)findViewById(R.id.btnlogin);
     //   ForgetPassword=(TextView)findViewById(R.id.tvForgetPassword);
        Signup=(TextView)findViewById(R.id.tvSignUp);
        progressDialog=new ProgressDialog(this);

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user!=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Username.getText().toString(),password.getText().toString());
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            }
        });



    }

    private void validate(String username,String Password)
    {
        progressDialog.setMessage("Verifing");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(username,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,SecondActivity.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login unsuccessful",Toast.LENGTH_SHORT).show();
                    counter--;
                  progressDialog.dismiss();
                    if (counter==0)
                    {
                        Login.setEnabled(false);
                    }
                }

            }
        });
    }

}

