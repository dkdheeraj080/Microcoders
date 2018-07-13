package com.example.dheeraj.microcoders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button Logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth=FirebaseAuth.getInstance();
        Logout=(Button)findViewById(R.id.btnLogout);


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(SecondActivity.this,MainActivity.class));

            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.LogoutMenu: {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(SecondActivity.this,MainActivity.class));

            }
        }
        return super.onOptionsItemSelected(item);
    }
}
