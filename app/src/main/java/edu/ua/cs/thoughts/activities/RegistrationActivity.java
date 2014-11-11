package edu.ua.cs.thoughts.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.database.DataSource;
import edu.ua.cs.thoughts.database.DatabaseHelper;
import edu.ua.cs.thoughts.entities.User;


public class RegistrationActivity extends Activity {

    private DataSource datasource;
    DatabaseHelper db;

    EditText etEmail, etPassword, etUsername;
    Button btnRegister, btnCurrentUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        datasource = new DataSource(this);
        datasource.open();

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnCurrentUsers = (Button) findViewById(R.id.btnCurrentUsers);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etUsername = (EditText) findViewById(R.id.etUsername);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User newUser = new User(etEmail.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString());
                datasource.addUser(newUser);

                // datasource.createThought("Hello", "testuser");

                // Creating tags
                // Tag tag1 = new Tag("Shopping");
                // Tag tag2 = new Tag("Important");
                // Tag tag3 = new Tag("Watchlist");
                // Tag tag4 = new Tag("Androidhive");

                // Inserting tags in db
                // long tag1_id = datasource.createTag(tag1);
                // long tag2_id = datasource.createTag(tag2);
                // long tag3_id = datasource.createTag(tag3);
                // long tag4_id = datasource.createTag(tag4);


                // UserDatabase.getInstance().addUser(newUser);
                //Thought hello = datasource.createThought(etEmail.getText().toString(), "testuser");

                //Toast toast = Toast.makeText(getApplicationContext(), "Welcome to Thoughts, " + newUser.getUsername(), Toast.LENGTH_SHORT);
                // Toast toast = Toast.makeText(getApplicationContext(), hello.thoughtText, Toast.LENGTH_SHORT);

               //toast.show();
            }
        });

        btnCurrentUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CurrentUsersActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.registration, menu);
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
