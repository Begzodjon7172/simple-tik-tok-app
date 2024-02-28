package uz.bnabiyev.simpletiktokapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.bnabiyev.simpletiktokapp.models.MyReels

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, "my_db", null, 1), MyDbService {
    override fun onCreate(db: SQLiteDatabase?) {
        var query =
            "create table video_table (id integer not null primary key autoincrement unique, title text not null, video_link text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun addReels(myReels: MyReels) {
        var database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("title", myReels.title)
        contentValues.put("video_link", myReels.videoLink)
        database.insert("video_table", null, contentValues)
        database.close()
    }

    override fun getAllReels(): ArrayList<MyReels> {
        var list = ArrayList<MyReels>()
        val database = this.readableDatabase
        var query = "select * from video_table"
        var cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val myReels = MyReels(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(myReels)
            } while (cursor.moveToNext())
        }

        return list
    }
}