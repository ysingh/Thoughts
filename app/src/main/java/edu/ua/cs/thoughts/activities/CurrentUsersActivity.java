package edu.ua.cs.thoughts.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;

import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.database.DataSource;
import edu.ua.cs.thoughts.entities.User;

public class CurrentUsersActivity extends ListActivity {
    private DataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_users_activity);

        datasource = new DataSource(this);
        datasource.open();

        List<User> values = datasource.getAllUsers();

        // Use a SimpleCursorAdapter to show List of Users
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}
