package com.example.user.project_tweekometer;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.user.project_tweekometer.ActionBar;

/**
 * Created by User on 3/2/2018.
 */

public class UserProfile extends AppCompatActivity {
    public double weight, age;
    public int caffeineSensitivity;
    public String userPassword;
    public Button smt;
    // Declaring the Toolbar Object
    private Toolbar toolbar;
    //Edit Text variables
    EditText userName,userAge, userWeight;
    RadioGroup userSensitivity;
    DatabaseHelper mDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_user_profile);


        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.child_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar ab = getSupportActionBar();

        mDatabaseHelper = new DatabaseHelper(this);

        ab.setDisplayHomeAsUpEnabled(true);
        smt = (Button)findViewById(R.id.smt);
        smt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                userName = (EditText) findViewById(R.id.userName);
                userAge = (EditText) findViewById(R.id.userAge);
                userWeight = (EditText) findViewById(R.id.userWeight);
                userSensitivity = (RadioGroup) findViewById(R.id.userSensitivity);
                RadioButton selected = (RadioButton) findViewById(userSensitivity.getCheckedRadioButtonId());
                caffeineSensitivity = -1;
                if(selected.getId() == R.id.r1)
                    caffeineSensitivity = 1;
                else if(selected.getId() == R.id.r2)
                    caffeineSensitivity = 2;
                else if(selected.getId() == R.id.r3)
                    caffeineSensitivity = 3;
                else if(selected.getId() == R.id.r4)
                    caffeineSensitivity = 4;
                else if(selected.getId() == R.id.r5)
                    caffeineSensitivity = 5;
                boolean addedData;
                addedData = mDatabaseHelper.addUser(
                        userName.getText().toString(),
                        userWeight.getText().toString(),
                        userAge.getText().toString(),
                        caffeineSensitivity );
                if(addedData)
                    Log.d("DB", "Success :o");
                else
                    Log.d("DB", "DENIED :o");
                submit();
            }
        });


    }

    public void submit(){
        Toast toast = Toast.makeText(getApplicationContext()," Info Saved! ", Toast.LENGTH_LONG );
        toast.show();
    }
}

