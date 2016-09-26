package com.example.admin.database1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    EditText userName,userPassword;
    Button login;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.name1);
        userPassword = (EditText) findViewById(R.id.password1);
        login = (Button) findViewById(R.id.login1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String pwd = userPassword.getText().toString();

                userDbHelper = new UserDbHelper(context);
                sqLiteDatabase = userDbHelper.getReadableDatabase();

                Cursor result = userDbHelper.getPassword(name,sqLiteDatabase);
                if(result.moveToFirst()){
                    if(pwd.equals(result.getString(1)))
                        Toast.makeText(context,"Successful Login",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context,"Wrong password",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(context,"Not a registered user",Toast.LENGTH_SHORT).show();

                userName.setText("");
                userPassword.setText("");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
