package com.example.student.moneyexpense;

        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    DBhelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = helper.getReadableDatabase();
       /* Cursor cursor = db.rawQuery("SELECT amount FROM expense;", null);
        cursor.moveToFirst();
        String totalcredit = cursor.getString(0); // get the first row

        TextView totalspend = (TextView)findViewById(R.id.textView);
        totalspend.setText(String.format("%.2f",totalcredit));*/

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
            case R.id.button:
                i = new Intent(this, add.class);
                startActivityForResult(i, 88);
                break;

            case R.id.button2:
                i = new Intent(this, recents.class);
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
}
