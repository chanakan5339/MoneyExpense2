package com.example.student.moneyexpense;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.content.Intent;
import android.view.View.OnLongClickListener;


public class recents extends ActionBarActivity

        implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener{

    DBhelper helper;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recents);

        helper = new DBhelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, amount, date||' ('||category||')' g FROM expense Order By date Desc",null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, // A textview
                cursor, // cursor to a data collection
                new String[] {"amount","g"}, // column to be displayed
                new int[] {android.R.id.text1,android.R.id.text2}, // ID of textview to display
                0);

        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter);


        lv.setOnItemLongClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void buttonClicked(View v) {
        int id = v.getId();
        Intent i;

        switch(id) {
            case R.id.button6:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;

            case R.id.button7:
                i = new Intent(this, add.class);
                startActivity(i);
                break;
        }
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
        Log.d("course", id + " is clicked");
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long id) {

        SQLiteDatabase db = helper.getWritableDatabase();

        int n = db.delete("expense",
                "_id = ?",
                new String[]{Long.toString(id)});

        if (n == 1) {
            Toast t = Toast.makeText(this.getApplicationContext(),
                    "Successfully deleted the selected item.",
                    Toast.LENGTH_SHORT);
            t.show();

            // retrieve a new collection of records
            Cursor cursor = db.rawQuery("SELECT _id, amount, date||' ('||category||')' g FROM expense Order By date Desc",null);

            // update the adapter
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_2, // A textview
                    cursor, // cursor to a data collection
                    new String[] {"amount","g"}, // column to be displayed
                    new int[] {android.R.id.text1,android.R.id.text2}, // ID of textview to display
                    0);

            adapter.changeCursor(cursor);
            ListView lv = (ListView)findViewById(R.id.listView);
            lv.setAdapter(adapter);

        }
        db.close();
        return true;
    }

}
