package com.example.user.project_tweekometer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AlertDialog;
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
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    // Declaring the Toolbar Object
    private Toolbar toolbar;

    //Adding Database Helper to connect with: Users, consumption, and products
    DatabaseHelper mDatabaseHelper;
    //ADD MAIN BUTTONS
    ImageButton btn1, btn2, btn3, btn4;

    //Caffeine and time variables
    Timer timer;
    double currentCaffeine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

        //Needed for database
        mDatabaseHelper = new DatabaseHelper(this);
        currentCaffeine = 0;
        // TODO : Replace above with a caffeineOnStartup();
        /*
        timer = new Timer(1000);
        timer.;
        */
        // TODO : TEST HERE FOR DB FUNCTIONALITY / MOVE
        boolean addedData = mDatabaseHelper.addUser("Abby", "222", "30", "2");
        if(addedData)
            Log.d("DB", "Success :o");
        else
            Log.d("DB", "DENIED :o");
        // TODO : DELETE ABOVE

        btn1 = (ImageButton)findViewById(R.id.Button01);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addCoffee();
            }
        });
        btn2 = (ImageButton)findViewById(R.id.Button02);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addCoke();
            }
        });
        btn3 = (ImageButton)findViewById(R.id.Button03);
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addRedBull();
            }
        });
        btn4 = (ImageButton)findViewById(R.id.Button04);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reset?");
        builder.setMessage("Are you sure you want to reset caffeine count?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                reset();
            }
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });

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
            builder.show();
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

    public void alterCaffeine(double amount){
        boolean addedCn = mDatabaseHelper.addConsumption(System.currentTimeMillis(), currentCaffeine);
        if(addedCn)
            Log.d("AlterC", "Success :o");
        else
            Log.d("AlterC", "DENIED :o");
    }

    public void addAwake()
    {
        currentCaffeine += 40;
        Toast toast = Toast.makeText(getApplicationContext(), "Awake added: cf" + currentCaffeine, Toast.LENGTH_LONG );
        alterCaffeine(currentCaffeine);
        toast.show();
    }
    public void addCoke()
    {
        currentCaffeine += 60;
        Toast toast = Toast.makeText(getApplicationContext(), "Coke added: cf" + currentCaffeine, Toast.LENGTH_LONG );
        toast.show();
        alterCaffeine(currentCaffeine);
    }
    public void addCoffee()
    {
        currentCaffeine += 45;
        Toast toast = Toast.makeText(getApplicationContext(), "Coffee added: cf" + currentCaffeine, Toast.LENGTH_LONG );
        toast.show();
        alterCaffeine(currentCaffeine);
    }
    public void addRedBull()
    {
        currentCaffeine += 80;
        Toast toast = Toast.makeText(getApplicationContext(), "Red Bull added: cf" + currentCaffeine, Toast.LENGTH_LONG );
        toast.show();
        alterCaffeine(currentCaffeine);
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
