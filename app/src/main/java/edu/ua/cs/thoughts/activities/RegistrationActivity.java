package edu.ua.cs.thoughts.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.entities.User;
import edu.ua.cs.thoughts.database.UsersDataSource;


public class RegistrationActivity extends Activity {

    private UsersDataSource datasource;
    EditText etEmail, etPassword, etUsername;
    Button btnRegister, btnCurrentUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        datasource = new UsersDataSource(this);
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

                // UserDatabase.getInstance().addUser(newUser);
                datasource.addUser(newUser);

                Toast toast = Toast.makeText(getApplicationContext(), "Welcome to Thoughts, " + newUser.getUsername(), Toast.LENGTH_SHORT);
                toast.show();
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
