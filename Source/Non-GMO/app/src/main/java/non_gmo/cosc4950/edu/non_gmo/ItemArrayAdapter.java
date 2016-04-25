package non_gmo.cosc4950.edu.non_gmo;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by mike on 4/24/16.
 */
public class ItemArrayAdapter extends ArrayAdapter {


    static class ItemViewHolder {
        TextView category;
    }

    public ItemArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
    }
}
