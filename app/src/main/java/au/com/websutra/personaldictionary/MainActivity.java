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


public class MainActivity extends AppCompatActivity {
    // For the SimpleCursorAdapter to match the UserDictionary columns to layout items.
    private static final String[] COLUMNS_TO_BE_BOUND = new String[]{
            UserDictionary.Words._ID,
            UserDictionary.Words.WORD,
            UserDictionary.Words.FREQUENCY
    };
    private static final int[] LAYOUT_ITEMS_TO_FILL = new int[]{
            R.id.textView_list_item_id,
            R.id.textView_list_item_word,
            R.id.textView_list_item_frequency
    };
    private ArrayList<DictionaryObject> list = new ArrayList<DictionaryObject>();
    private ListView listView;
    private SimpleCursorAdapter cursorAdapter;
    private TextView tHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the TextView which will be populated with the Dictionary ContentProvider data.
        tHeading = (TextView) findViewById(R.id.textView_dictionary);
        listView = (ListView) findViewById(R.id.listView_dictionary);
        // Get the ContentResolver which will send a message to the ContentProvider
        ContentResolver resolver = getContentResolver();
        // Get a Cursor containing all of the rows in the Words table
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);
        Log.d("Test", UserDictionary.Words.CONTENT_URI.toString());
        tHeading.setText("The UserDictionary contains: "+cursor.getCount()+" entries");
        // Set the Adapter to fill the list_item layout with data from the Cursor.
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor, COLUMNS_TO_BE_BOUND, LAYOUT_ITEMS_TO_FILL, 0);
        // Attach the adapter to the ListView.
        listView.setAdapter(cursorAdapter);
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
