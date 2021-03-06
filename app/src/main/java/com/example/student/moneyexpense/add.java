package com.example.student.moneyexpense;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class add extends ActionBarActivity {

    private String[] arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        this.arraySpinner = new String[]{
                "Today", "Yesterday", "2 Days Ago", "3 Days Ago", "4 Days Ago", "5 Days Ago"
                , "6 Days Ago", "7 Days Ago"
        };
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

        this.arraySpinner = new String[]{
                "General", "Clothes", "Food", "Rent", "House", "Insurance", "Health",
                "Travel", "Leisure", "Pet", "Fuel", "Car", "Education", "Sport",
                "Music"
        };
        Spinner s2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s2.setAdapter(adapter2);
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

        switch (id) {
            case R.id.button3:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;

            case R.id.button4:
                i = new Intent(this, recents.class);
                startActivity(i);
                break;
        }
    }

    public void addClicked(View v) {
        EditText editText = (EditText) findViewById(R.id.editText);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);


        String text = editText.getText().toString();
        String spinner_1 = spinner.getSelectedItem().toString();
        String spinner_2 = spinner2.getSelectedItem().toString();


        Toast t = Toast.makeText(this.getApplicationContext(),
                "Added",
                Toast.LENGTH_SHORT);
        t.show();

        DBhelper helper = new DBhelper(this);

        ContentValues r = new ContentValues();


        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        SQLiteDatabase db = helper.getWritableDatabase();
        //ContentValues r = new ContentValues();
        r.put("amount", text);
        if(spinner_1.toString().equals("Today"))
        {
            r.put("date", getCalculatedDate("dd/MM/yyyy",0));
        }
        else if(spinner_1.toString().equals("Yesterday"))
        {
            r.put("date", getCalculatedDate("dd/MM/yyyy",-1));
        }
        else if(spinner_1.toString().equals("2 Days Ago"))
        {
            r.put("date", getCalculatedDate("dd/MM/yyyy",-2));
        }
        else if(spinner_1.toString().equals("3 Days Ago"))
        {
            r.put("date", getCalculatedDate("dd/MM/yyyy",-3));
        }
        else if(spinner_1.toString().equals("4 Days Ago"))
        {
            r.put("date", getCalculatedDate("dd/MM/yyyy",-4));
        }
        else if(spinner_1.toString().equals("5 Days Ago"))
        {
            r.put("date", getCalculatedDate("dd/MM/yyyy",-5));
        }
        else if(spinner_1.toString().equals("6 Days Ago"))
        {
            r.put("date", getCalculatedDate("dd/MM/yyyy",-6));
        }
        else if(spinner_1.toString().equals("7 Days Ago"))
        {
            r.put("date", getCalculatedDate("dd/MM/yyyy",-7));
        }

        r.put("category", spinner_2);
        db.insert("expense", null, r);
        }

    public String getCalculatedDate(String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(cal.getTimeInMillis()));
    }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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


