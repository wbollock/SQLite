package wbollock.com.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper { // class just to work with the database

    private static final int DATABASE_VERSION = 1; // if you change database structure, need to change version #
    private static final String DATABASE_NAME = "products.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTNAME + " TEXT" +
                ");"; // creating query for database
        sqLiteDatabase.execSQL(query); // executing the query itself
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);
    }

    //Add a new row to the database
    public void addProduct(Products product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productname()); // put productname into COLUMN
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values); // inserts a new row into table
        sqLiteDatabase.close(); // gives android the memory back when we're done with db
    }
    // delete a product from the database
    public void deleteProduct(String productName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }
        //print out the database as a string
        public String databaseToString() {
            String dbString = "";
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

            //Cursor (sort of a pointer) points to a location in your results
            Cursor c = sqLiteDatabase.rawQuery(query, null);
            // move to first row in your results
            c.moveToFirst();

            while(!c.isAfterLast()){ // make sure there are still some results to go
                if(c.getString(c.getColumnIndex("productname"))!= null){
                    dbString += c.getString(c.getColumnIndex("productname"));
                    dbString += "\n";
                }
                c.moveToNext();
            }
            sqLiteDatabase.close();
            return dbString;
    }


}
