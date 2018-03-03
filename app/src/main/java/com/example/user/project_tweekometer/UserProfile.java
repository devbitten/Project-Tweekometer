package com.example.user.project_tweekometer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by User on 3/2/2018.
 */

public class UserProfile extends AppCompatActivity {
    public double weight, age;
    public int caffeineSensitivity;
    public String userName, userPassword;
    public Button smt;
    // Declaring the Toolbar Object
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

        smt = (Button)findViewById(R.id.smt);
        smt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               submit(v);
            }
        });


    }

    public void submit(android.view.View v){

    }
}

