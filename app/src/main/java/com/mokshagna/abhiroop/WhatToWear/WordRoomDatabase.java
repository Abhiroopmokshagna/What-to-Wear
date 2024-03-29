package com.mokshagna.abhiroop.WhatToWear;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities={Word.class},version=1,exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized(WordRoomDatabase.class){
                if(INSTANCE==null){
                   INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WordRoomDatabase.class,"clothes_database")
                           .fallbackToDestructiveMigration()
                           .addCallback(sRoomDatabaseCallback)
                           .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;
        String[] words = new String[7]; //= {"dolphin", "crocodile", "cobra"};
        String[] days = new String[7];//= {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            //mDao.deleteAll();
//            if(mDao.getAnyWord().length<1) {
//                for (int i = 0; i <= words.length - 1; i++) {
//                    Word word = new Word(words[i],days[i]);
//                    mDao.insert(word);
//                }
//            }
            return null;
        }
    }
}
