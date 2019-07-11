package com.mokshagna.abhiroop.WhatToWear;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
@Entity(tableName = "word_table")
public class Word {
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "day")
    private String mDay;
    public Word(@NonNull String word,@NonNull String day){
        this.mWord = word;
        this.mDay = day;
    }
    public String getWord(){
        return this.mWord;
    }
    public String getDay(){
        return this.mDay;
    }
}
