package edu.ua.cs.thoughts;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.os.Handler;


public class SplashActivity extends Activity {

    private static final int SPLASH_DISPLAY_TIME = 2000; // splash screen delay time

    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        // Remove action bar
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, LoginActivity.class);

                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();

                // transition from splash to main menu
                overridePendingTransition(R.anim.activityfadein,
                R.anim.splashfadeout);

            }
        }, SPLASH_DISPLAY_TIME);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
