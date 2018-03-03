package com.example.user.project_tweekometer;
/**
 * Created by User on 3/3/2018.
 */
import java.lang.*;
import java.util.*;

public class CaffeineFormulas {
    private static double startTime = (System.currentTimeMillis()/1000);// start time seconds
    private static double current = (System.currentTimeMillis()/1000);//current total seconds
    private static double deltaCurrent = 0;  // current change in seconds
    private static double concentration = 0;  // concentration of caffeine in mg/kg
    private static double tolerence = 0;  // tolerance value
    private static double weight = 0;  // weight in kg
    final static double HALFLIFE = 18000;  //  5 hours in seconds

    public static void main(String[] args) {

    }
    static public double addCaffeine(double addCaff)// add caffeine mg/kg
    {
        concentration += (addCaff / weight);
        return concentration;
    }
    static public void setWeight(double pound) //convert pounds to kg
    {
        weight = (pound/2.2);
    }
    public static double currentCaff() //calculate current caff for time expired
    {
        double exponent;
        exponent = ((Math.log(2))/HALFLIFE)*(-1 *getTime());
        concentration = concentration * Math.exp(exponent);
        return concentration;
    }
    public static double sleepTime() {  // time til caffeine less than .5 mg/kg
        double timeSleep = 0;
        double goal = 0.5;
        double ratio = goal/concentration;
        timeSleep = (-1 * HALFLIFE * Math.log(ratio))/Math.log(2);
        if(timeSleep >= 0)
            return timeSleep;
        else return 0;
    }
    public static double goodTime() //time til caffeine les than 3 mg/kg
    {
        double timeGood = 0;
        double goal = 3;
        double ratio = goal/concentration;
        timeGood = (-1 * HALFLIFE * Math.log(ratio))/Math.log(2);
        if(timeGood >= 0)
            return timeGood;
        else return 0;
    }

    public static int level() // get level of caffeine effect
    {
        if((currentCaff()*tolerence)>40)// possible death
            return 5;
        else if((currentCaff()*tolerence)<=40 && (currentCaff()*tolerence)>10)//overdose side effects
            return 4;
        else if((currentCaff()*tolerence)<=10 && (currentCaff()*tolerence)>3)//too much some side effects
            return 3;
        else if((currentCaff()*tolerence)<=3 && (currentCaff()*tolerence)>0.5)//therepudic dose
            return 2;
        else if ((currentCaff()*tolerence)<= 0.5 && (currentCaff()*tolerence)>=0)// negligeble
            return 1;
        else return 0;
    }

    public static void setTolerence(double tol) //set sensitivity
    {
        tolerence = tol;
    }
    public static double getTime() // time elapsed since last check in seconds
    {
        deltaCurrent = (System.currentTimeMillis()/1000) - current;
        current  += deltaCurrent;
        return deltaCurrent;
    }
    
    public static double checkTime() //return total time elapsed in seconds
    {
        return (((System.currentTimeMillis()/1000)-startTime)/60);
    }
}
