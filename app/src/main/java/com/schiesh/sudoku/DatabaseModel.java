package com.schiesh.sudoku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseModel extends SQLiteOpenHelper {

    public static final String PLAYER_TABLE = "PLAYER_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_PLAYER_NAME = "PLAYER_NAME";
    public static final String COLUMN_PLAYER_SCORE = "PLAYER_SCORE";

    public DatabaseModel(Context context) {

        super(context, "Player.db", null, 1);
    }

    //database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createtable = "CREATE TABLE " + PLAYER_TABLE + " ( ID  INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ID + COLUMN_PLAYER_NAME + " TEXT, " + COLUMN_PLAYER_SCORE + " INT) ";
        db.execSQL(createtable);

    }

    //database is updated
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(PlayerModel playermodel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PLAYER_NAME, playermodel.getName());
        cv.put(COLUMN_PLAYER_SCORE, playermodel.getScore());
        long insert = db.insert(PLAYER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public List<PlayerModel> getEveryone(){
        List<PlayerModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + PLAYER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            //loop through results and create new player objects. insert in the return list.
            do{
                int playerID = cursor.getInt(0);
                String playername = cursor.getString(1);
                int playerscore = cursor.getInt(2);

                PlayerModel newPlayer = new PlayerModel(playerID, playername, playerscore);
                returnList.add(newPlayer);


            } while (cursor.moveToNext());
        }
        else{
            //fails, doesn't add any values.
        }
        //close cursor
        cursor.close();
        db.close();
        return returnList;
    }
}