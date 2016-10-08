package com.gu.vishal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishal on 08/10/2016.
 */
public class SqlDbHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="db_blog";
    String TABLE_POST="TB_POST";

    private static final String KEY_ID = "id";
    String KEY_AUTHOR = "author";
    String KEY_POST = "post";




    public SqlDbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    public SqlDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POST_TABLE = "CREATE TABLE " + TABLE_POST + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_AUTHOR + " TEXT, " + KEY_POST + " TEXT)";
        db.execSQL(CREATE_POST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //drop table if exists
        db.execSQL("DROP TABLE "+ TABLE_POST);

        //create new
        onCreate(db);
    }

    public void addBlog(SqlBlog blog){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_AUTHOR,blog.get_author());
        values.put(KEY_POST,blog.get_post());

        db.insert(TABLE_POST, null, values);
        db.close();
    }

    public List<SqlBlog> getAllPosts(){
        List<SqlBlog> post_list = new ArrayList<SqlBlog>();

        String selectQuery = "SELECT * FROM " + TABLE_POST;
        SQLiteDatabase db = this.getWritableDatabase();

        //cursor exposes results from db query.
        Cursor cursor = db.rawQuery(selectQuery,null);

        //Looping thru all rows and adding to list
        //move cursor to first row

        if (cursor.moveToFirst()){
            do {
                SqlBlog blog_entry = new SqlBlog();
                blog_entry.set_id(cursor.getInt(0));
                blog_entry.set_author(cursor.getString(1));
                blog_entry.set_post(cursor.getString(2));

                // add blog to list
                post_list.add(blog_entry);
            }while (cursor.moveToNext());
        }
        //return blog entry list.
        return post_list;
    }


}
