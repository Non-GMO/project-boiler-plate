package non_gmo.cosc4950.edu.non_gmo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Bridger on 4/29/2016.
 */
public class ProductsDatabase {

    private MyDBHandler DBHelper;
    private SQLiteDatabase db;
    String TAG = "PRODUCTSDATABASE";

    //constructor
    public ProductsDatabase(Context ctx) {
        Log.d(TAG, "Made it to constructor");
        DBHelper = new MyDBHandler(ctx);
    }

    //---opens the database---
    public void open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        Log.d(TAG, "Database opened and initialized");
    }

    //returns true if db is open.  Helper method.
    public boolean isOpen() throws SQLException {
        return db.isOpen();
    }

    //---closes the database---
    public void close() {
        DBHelper.close();
        db.close();
    }


    /**
     * This projects all columns, selects all entries where productname is Dicks,
     * and sorts the results by productname
     * @return
     */
    public Cursor getRow1() {
        Cursor mCursor = Query(MyDBHandler.TABLE_NAME,   //table name
                new String[]{MyDBHandler.UPC, MyDBHandler.PRODUCT_NAME, MyDBHandler.BRAND_NAME, MyDBHandler.CATEGORY},  //projection, ie columns.
                MyDBHandler.PRODUCT_NAME + " = 'Dicks'",  //selection,  we want everything.
                null, // String[] selectionArgs,  again, we want everything.
                MyDBHandler.PRODUCT_NAME// String sortOrder  by name as the sort.
        );
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    public long insertRow1(String upc, String product, String brand, String category) {
        ContentValues values = new ContentValues();
        values.put(MyDBHandler.UPC, upc);
        values.put(MyDBHandler.PRODUCT_NAME, product);
        values.put(MyDBHandler.BRAND_NAME, brand);
        values.put(MyDBHandler.CATEGORY, category);
        return db.insert(MyDBHandler.TABLE_NAME, null, values);
    }


    /**
     * Loads included .csv into database
     * @param ctx
     */
    public void loadCSV(Context ctx) {
        Log.d(TAG, "Made it to loadCSV");

        InputStream is = null;
        try {
            is = ctx.getAssets().open("products.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String line = "";
        String tableName = MyDBHandler.TABLE_NAME;
        String columns = MyDBHandler.UPC + ", " + MyDBHandler.PRODUCT_NAME
                + ", " + MyDBHandler.BRAND_NAME + ", " + MyDBHandler.CATEGORY;
        String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
        String str2 = ");";

        try {
            while ((line = br.readLine()) != null) {
                StringBuilder sb = new StringBuilder(str1);
                String[] str = line.split(",");
                sb.append("'" + str[0] + "', '");
                sb.append(str[1] + "', '");
                sb.append(str[2] + "', '");
                sb.append(str[3] + "'");
                sb.append(str2);
                db.execSQL(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG,"Finished loadCSV");
    }


    /**
     * Returns Category column as result
     * @return
     */
    public Cursor getCategories() {
        Cursor c = Query(MyDBHandler.TABLE_NAME,
                new String[]{MyDBHandler.CATEGORY},
                null,
                null,
                MyDBHandler.CATEGORY);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


    public boolean numpadSearch(String upc) {
        Cursor c = Query(MyDBHandler.TABLE_NAME,
                new String[]{MyDBHandler.UPC},
                MyDBHandler.UPC + " = " + upc,
                null,
                MyDBHandler.CATEGORY);
        if (c != null) {
            return true;
        } else {
            return false;
        }
    }


    public Cursor Query(String TableName, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TableName);
        return qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
    }


}
//....................../´¯/)
//....................,/¯../
//.................../..../
//............./´¯/'...'/´¯¯`·¸
//........../'/.../..../......./¨¯\
//........('(...´...´.... ¯~/'...')
//.........\.................'...../
//..........''...\.......... _.·´
//............\..............(
//..............\.............\...