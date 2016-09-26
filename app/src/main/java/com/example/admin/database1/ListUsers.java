package com.example.admin.database1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class ListUsers extends ActionBarActivity {

    ListView mylist;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    ListDataAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        mylist = (ListView) findViewById(R.id.listView);
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        Cursor results = userDbHelper.getInformation(sqLiteDatabase);
        listAdapter = new ListDataAdapter(getApplicationContext(),R.layout.customlist);
        mylist.setAdapter(listAdapter);

        if(results.moveToFirst()){

            do{
                String name,password;
                name = results.getString(0);
                password = results.getString(1);

                DataProvider dp =new DataProvider(name,password);
                listAdapter.add(dp);

            }while (results.moveToNext());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_users, menu);
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
