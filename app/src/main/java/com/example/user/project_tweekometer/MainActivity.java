package com.example.user.project_tweekometer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
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
import android.widget.TextView;
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
    double currentCaffeine = 0;
    // TODO : Replace following section with good variables
    //MASTER_TIMER
    //TIL_GOOD_TIMER
    //TIL_SLEEP_TIMER
    int weightBAD = 50;
    int currentCafBAD = 30;
    
    // TODO : DELETE ABOVE
    //ADD MAIN BUTTONS
    ImageButton btn1, btn2, btn3, btn4;

    //Caffeine and time variables
    private static double currentCaffeine;
    private TextView mTextTime;
    private Handler handler = new Handler();
    private int timeElapsed;
    //long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
   // int Seconds, Minutes, MilliSeconds;
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
        //TIMER BELOW
        mTextTime = (TextView) findViewById(R.id.m_timer);
        timeElapsed = 0;

        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("DDR", "IT WAS CALLED");
                handler.postDelayed(this, 1000);
                timeElapsed += 1;
                if(timeElapsed%60 < 10)
                    mTextTime.setText( "" + (timeElapsed/60) + ":0" + (timeElapsed%60));
                else
                    mTextTime.setText( "" + (timeElapsed/60) + ":" + (timeElapsed%60));
            }
        };

        handler.postDelayed(runnable, 1000);
        // TODO : TEST HERE FOR DB FUNCTIONALITY / MOVE
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

  /*  public void Tick(){
            StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);


    }
    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis();

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            text.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };*/

  /* TEST DEL
  public void textloop()
  {
      for (int i = 0; i < 5; i++)
      {
          TextView time=findViewById(R.id.timer);
          time.setText(String.valueOf(i));
      }
  }


    public void DisplayTime(int t)
    {
        TextView TIME = (TextView)findViewById(R.id.timer);
        TIME.setText(String.valueOf(t));
    }
*/ //TEST DEL//


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

    /* TODO : DELETE ??
      public void loadProducts(View view){

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
    */

    /** This function is meant to initiate the countdown of the:
     *   masterTimer, tilGoodTimer, tilSleepTimer, currentCaffeine
     *   and currentConcetration
     *
     * @param lastTime  Last time a value was added to the database
     * @param lastCaf   Last recorded caffeine value
     */
    public void initiateCaffeine(long lastTime, double lastCaf){
        // TODO : INITIATE
    }

    /** This is meant to be run every time caffeine is added to body.
     *   This updates the: currentCaffeine, and currentConcentration
     *   Also record this ingestion to the database.
     * @param amount    Amount of caffeine in body currently
     */ // TODO : WRITE
    public void alterCaffeine(double amount){
        boolean addedCn = mDatabaseHelper.addConsumption(System.currentTimeMillis(), currentCaffeine);
        if(addedCn)
            Log.d("AlterC", "Success :o");
        else
            Log.d("AlterC", "DENIED :o");
    }

    /** This is meant to be run every second that the Timer increments by a second.
     *   This updates the: masterTimer, tilGoodTimer, tilSleepTimer,
     *   currentCaffeine, and currentConcentration
     *
     * @param amount    Amount of caffeine in body one second ago
     */ // TODO : WRITE
    public void refreshCaffeine(double amount){
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
