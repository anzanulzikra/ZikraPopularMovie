package zikra.popularmovie;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;
    private boolean InternetCheck=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //progressBar
        PostDelayedMethod();

    }


    public void PostDelayedMethod()
    {

        new Handler().postDelayed(new Runnable() {


            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                // This method will be executed once the timer is over
                // Start your app main activity

                boolean InternetResult = checkConnection();
                if(InternetResult){

                    //open Activity when internet is connected
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);//WelcomeActivity
                    //     intent.addCategory(Intent.CATEGORY_HOME);
                    //    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }
                else {



                    //Dialog Box show when internet is not connected
                    DialogAppear();


                }
            }
        }, SPLASH_TIME_OUT);
    }


    //DialogBox Main Function
    public void DialogAppear()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                SplashActivity.this);

        builder.setTitle("Network Error");   //Title
        builder.setMessage("Tidak Ada Koneksi Internet");   //Message


        //Negative Message
        builder.setNegativeButton("Keluar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                                    /* close this activity
                                    *  When Exit is clicked
                                    */
                        finish();

                    }
                });

        //Positive Message
//        builder.setPositiveButton("Coba Lagi",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,
//                                        int which) {
//
//                        //Check internet again when click on Retry by calling function
//
//                        //run is not working there due to runnable method
//                        // run();
//                        PostDelayedMethod();
//
//                    }
//                });
        builder.show();
    }


    //Check Internet status of the mobile
    protected boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    //Return Internet Status of the Mobile
    public boolean checkConnection(){
        if(isOnline()){
            return InternetCheck;

        }else{
            InternetCheck=false;
            return InternetCheck;


        }

    }
}
