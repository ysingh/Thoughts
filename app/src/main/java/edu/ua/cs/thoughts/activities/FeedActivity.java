package edu.ua.cs.thoughts.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.database.DataSource;
import edu.ua.cs.thoughts.entities.Thought;
import edu.ua.cs.thoughts.fragments.AddThoughtFragment;
import edu.ua.cs.thoughts.fragments.ListFeedFragment;
import edu.ua.cs.thoughts.fragments.NavigationDrawerFragment;
import edu.ua.cs.thoughts.fragments.ViewSingleThoughtFragment;
import edu.ua.cs.thoughts.interfaces.SingleThoughtInterface;


public class FeedActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks, SingleThoughtInterface {

    public static String userEmail, username;
    public static DataSource dataSource;

    @Override
    public void launchThoughtFragment(Thought thought) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, ViewSingleThoughtFragment.newInstance(thought));
        ft.commit();
    }

    interface MyCallbackClass{
        void callbackReturn();
    }

    MyCallbackClass myCallbackClass;

    void registerCallback(MyCallbackClass callbackClass){
        myCallbackClass = callbackClass;
    }


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        userEmail = getIntent().getExtras().getString("email");

        dataSource = new DataSource(this);
        dataSource.open();

        username = dataSource.getUsernameGivenEmail(userEmail);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getFragmentManager();

        switch (position) {
            case 0: {
                fragmentManager.beginTransaction().replace(R.id.container, ListFeedFragment.newInstance())
                        .commit();
                break;
            }

            case 1: {
                fragmentManager.beginTransaction().replace(R.id.container, AddThoughtFragment.newInstance())
                        .commit();
                break;
            }

            default: {
                // update the main content by replacing fragments
                fragmentManager.beginTransaction().replace(R.id.container, ListFeedFragment.newInstance())
                        .commit();
            }

        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.feed, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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
