package non_gmo.cosc4950.edu.non_gmo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class ListActivity extends AppCompatActivity {

    CSVAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // TODO: POPULATE LIST WITH DATABASE
        //Lookup our ListView
        ListView mList = (ListView)findViewById(R.id.listView);

        //Create Adapter. The second parameter is required by ArrayAdapter
        //which our Adapter extends. In this example though it is unused,
        //so we'll pass it a "dummy" value of -1.
        mAdapter = new CSVAdapter(this, -1);

        //attach our Adapter to the ListView. This will populate all of the rows.
        mList.setAdapter(mAdapter);

        /*
		 * This listener will get a callback whenever the user clicks on a row.
		 * The pos parameter will tell us which row got clicked.
		 *
		 * For now we'll just show a Toast with the state capital for the state that was clicked.
		 */
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                Toast.makeText(v.getContext(), mAdapter.getItem(pos).getCategory(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}