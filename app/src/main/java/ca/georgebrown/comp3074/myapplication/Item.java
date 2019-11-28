package ca.georgebrown.comp3074.myapplication;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Item {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "item")

    private String mItem;

    public Item(@NonNull String mItem){
        this.mItem = mItem;
    }

    public String getItem(){return this.mItem;}

    public void setItem(@NonNull String mItem) {
        this.mItem = mItem;
    }
}
