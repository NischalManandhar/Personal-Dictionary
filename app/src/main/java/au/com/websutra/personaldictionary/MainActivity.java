package au.com.websutra.personaldictionary;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import au.com.websutra.personaldictionary.Adapters.MyListViewAdapter;


public class MainActivity extends AppCompatActivity {
    private ArrayList<DictionaryObject> list = new ArrayList<DictionaryObject>();
    private ListView listView;
    private TextView tHeading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the TextView which will be populated with the Dictionary ContentProvider data.
        tHeading=(TextView)findViewById(R.id.textView_dictionary);
        listView = (ListView) findViewById(R.id.listView_dictionary);
        // Get the ContentResolver which will send a message to the ContentProvider
        ContentResolver resolver = getContentResolver();
        // Get a Cursor containing all of the rows in the Words table
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);
        Log.d("Test", UserDictionary.Words.CONTENT_URI.toString());
        // Surround the cursor in a try statement so that the finally block will eventually execute
        // Surround the cursor in a try statement so that the finally block will eventually execute
        try {
            // Get the index of the column containing the actual words, using
            // UserDictionary.Words.WORD, which is the header of the word column.
            int wordColumnIndex = cursor.getColumnIndex(UserDictionary.Words.WORD);
            int frequencyColumnIndex = cursor.getColumnIndex(UserDictionary.Words.FREQUENCY);
            int idColumnIndex = cursor.getColumnIndex(UserDictionary.Words._ID);

            // Iterates through all returned rows in the cursor.
            while (cursor.moveToNext()) {
                // Use that index to extract the String value of the word
                // at the current row the cursor is on.
                DictionaryObject temp = new DictionaryObject();
                temp.id = cursor.getInt(idColumnIndex);
                temp.frequency = cursor.getInt(frequencyColumnIndex);
                temp.word = cursor.getString(wordColumnIndex);
                //Log.d("Each row: ", temp.id + "  " + temp.word + "  " + temp.frequency);
                list.add(temp);
            }
            tHeading.setText("The user dictionary contains:  "+cursor.getCount()+" entries");
            listView.setAdapter(new MyListViewAdapter(this, list));
        } finally {
            // Always close your cursor to avoid memory leaks
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
