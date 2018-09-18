package beans.dreamteam.musicbeans;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;


public class SplashActivity extends AppCompatActivity {

    /** Time in milliseconds that splash screen will be displayed **/
    private final int SPLASH_DELAY_LENGTH = 1500;

    private LinearLayout llContainer;
    private LinearLayout llContainer2;
    private Animation slide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);/** By default lock screen in portrait orientation. **/
        setContentView(R.layout.activity_splash_screen);
        llContainer = findViewById(R.id.splash_llContainer);
        llContainer2 = findViewById(R.id.splash_llContainer2);


        llContainer2.post(new Runnable() {
            @Override
            public void run() {
                slide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_slide2);
                llContainer2.startAnimation(slide);
            }
        });

        llContainer.post(new Runnable() {
            @Override
            public void run() {
                slide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_slide);
                llContainer.startAnimation(slide);
            }
        });
        startDelayedMethod();
    }

    public void startDelayedMethod() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                try
                {
                    startDelayedIntent();
                }
                catch(Exception ew)
                {
                    Log.e("SPLASH", "" + ew.toString());
                }
            }
        }, 1000);
    }

    public void startDelayedIntent() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                try
                {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch(Exception ew)
                {
                    Log.e("SPLASH", "" + ew.toString());
                }
            }
        }, SPLASH_DELAY_LENGTH);
    }


}