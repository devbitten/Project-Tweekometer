package com.example.user.project_tweekometer;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {
    // Declaring the Toolbar Object
    private Toolbar toolbar;

    //Adding Database Helper to connect with: Users, consumption, and products
    DatabaseHelper mDatabaseHelper;
    //ADD MAIN BUTTONS
    ImageButton btn1, btn2, btn3, btn4;
    // TODO : DELETE THIS, images should be 300x300
    // TODO : DO SOMETHING WITH JSON PARSER

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

        // TODO : TEST HERE FOR DB FUNCTIONALITY
        mDatabaseHelper = new DatabaseHelper(this);
        boolean addedData = mDatabaseHelper.addUser("Abby", "222", "30", "2");
        if(addedData)
            Log.d("DB", "Success :o");
        else
            Log.d("DB", "DENIED :o");

        btn1 = (ImageButton)findViewById(R.id.coffeeButton);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addCoffee();
            }
        });
        btn2 = (ImageButton)findViewById(R.id.cokeButton);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addCoke();
            }
        });
        btn3 = (ImageButton)findViewById(R.id.RedBullButton);
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addRedBull();
            }
        });
        btn4 = (ImageButton)findViewById(R.id.awakeButton);
        btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addAwake();
            }
        });

        // TODO : REMOVE (USING TO DEBUG PRODUCTS JSON)
        loadProducts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            toSettings();
            return true;
        }
        else if (id == R.id.action_profile)
        {
            //GOES HERE
            toProfile();
            //AND HERE
            return true;
        }
        else if (id == R.id.action_reset) {
            reset();
            return true;
        }

        else
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method loads the 'Product' content
     */
    public void loadProducts(/*View view*/){

        Resources res = getResources();

        InputStream is = res.openRawResource(R.raw.products);

        Scanner scanner = new Scanner(is);

        StringBuilder builder = new StringBuilder();

        while(scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }

        String j = builder.toString();
        try {
            JSONObject root = new JSONObject(j);
            Log.d("JSON OBJ", root.getJSONObject("Products").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void addAwake()
    {
        Toast toast = Toast.makeText(getApplicationContext(), "Awake added", Toast.LENGTH_LONG );
        toast.show();
    }
    public void addCoke()
    {
        Toast toast = Toast.makeText(getApplicationContext(), "Coke added", Toast.LENGTH_LONG );
        toast.show();
    }
    public void addCoffee()
    {
        Toast toast = Toast.makeText(getApplicationContext(), "Coffee added", Toast.LENGTH_LONG );
        toast.show();
    }
    public void addRedBull()
    {
        Toast toast = Toast.makeText(getApplicationContext(), "RedBull added", Toast.LENGTH_LONG );
        toast.show();
    }
    public void reset()
    {
        
    }
    public void toProfile()
    {

        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    }
    public void toSettings()
    {

    }
}
