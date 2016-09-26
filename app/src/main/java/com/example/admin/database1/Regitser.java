package com.example.admin.database1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Regitser extends ActionBarActivity {

    EditText name,password;
    Button register;
    Context context = this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regitser);

        name = (EditText) findViewById(R.id.name2);
        password = (EditText) findViewById(R.id.password2);
        register = (Button) findViewById(R.id.login2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String userPassword = password.getText().toString();

                userDbHelper = new UserDbHelper(context);
                sqLiteDatabase = userDbHelper.getWritableDatabase();

                userDbHelper.addData(userName,userPassword,sqLiteDatabase);
                Toast.makeText(getApplicationContext(),"User registered sucessfully",Toast.LENGTH_SHORT).show();
                userDbHelper.close();

                name.setText("");
                password.setText("");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_regitser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
