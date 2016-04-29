package non_gmo.cosc4950.edu.non_gmo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Bridger on 4/28/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper{


    String TAG = "MyDBHandler";

    //column names for the table.
    public static final String BRAND_NAME = "BrandName";
    public static final String PRODUCT_NAME = "ProductName";
    public static final String CATEGORY = "Category";
    public static final String UPC = "_id";   //required field for the cursorAdapter

    private static final String DATABASE_NAME = "products.db";
    public static final String TABLE_NAME = "products";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    UPC + " TEXT, " +
                    PRODUCT_NAME + " TEXT, " +
                    BRAND_NAME + " TEXT, " +
                    CATEGORY + " TEXT);";

    //required constructor with the super.
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //NOTE only called when the database is initial created!
        db.execSQL(DATABASE_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Called when the database version changes, Remember the constant from above.
        Log.w(TAG, "Upgrading database from version " + oldVersion
                + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}