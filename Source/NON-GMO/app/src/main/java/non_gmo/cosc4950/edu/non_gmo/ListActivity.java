package non_gmo.cosc4950.edu.non_gmo;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;


public class ListActivity extends AppCompatActivity {

    CSVAdapter mAdapter;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // TODO: POPULATE LIST WITH DATABASE
        //Lookup our ListView
        ListView mList = (ListView)findViewById(R.id.listView);


        ProductsDatabase db = new ProductsDatabase(this);
        db.open();
        Log.d("LISTACTIVITY","About to do getCategories");
        Cursor c = db.getCategories();
        Log.d("LISTACTIVITY","Did getCategories");
        db.close();
        //String[] strings = new String[]{};
        ArrayList<String> categories = new ArrayList<>();
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.single_list_item, categories);
        String temp = "";
        while (c.moveToNext()) {
            if (Objects.equals(temp, c.getString(c.getColumnIndex(MyDBHandler.CATEGORY)))) {

            } else {
                listAdapter.add(c.getString(c.getColumnIndex(MyDBHandler.CATEGORY)));
                temp = c.getString(c.getColumnIndex(MyDBHandler.CATEGORY));
            }
        }
        mList.setAdapter(listAdapter);



        /*
        //Create Adapter. The second parameter is required by ArrayAdapter
        //which our Adapter extends. In this example though it is unused,
        //so we'll pass it a "dummy" value of -1.
        mAdapter = new CSVAdapter(this, -1);

        //attach our Adapter to the ListView. This will populate all of the rows.
        mList.setAdapter(mAdapter);
        */

        /*
		 * This listener will get a callback whenever the user clicks on a row.
		 * The pos parameter will tell us which row got clicked.
		 *
		 * For now we'll just show a Toast with the state capital for the state that was clicked.
		 */
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                //TODO: GET CATEGORY TAPPED, QUERY DATABASE FOR ALL PRODUCTS IN THAT CATEGORY, REPOPULATE LISTVIEW
                Toast.makeText(v.getContext(), mAdapter.getItem(pos).getCategory(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}