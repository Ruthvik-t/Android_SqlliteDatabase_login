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


public class DeleteUser extends ActionBarActivity {

    EditText uname,upwd;
    Button del;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        uname = (EditText) findViewById(R.id.name3);
        upwd = (EditText) findViewById(R.id.password3);
        del = (Button) findViewById(R.id.delete);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = uname.getText().toString();
                String p = upwd.getText().toString();

                userDbHelper = new UserDbHelper(c);
                sqLiteDatabase = userDbHelper.getReadableDatabase();
                Cursor result = userDbHelper.getPassword(n,sqLiteDatabase);

                if(result.moveToFirst()){
                    if(p.equals(result.getString(1))){
                        int r = userDbHelper.deleteUser(n,sqLiteDatabase);
                        if(r==1)
                            Toast.makeText(c,"Deleted Successfully",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(c, "Wrong password", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(c,"U are not a Registered User",Toast.LENGTH_SHORT).show();

                uname.setText("");
                upwd.setText("");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete_user, menu);
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
