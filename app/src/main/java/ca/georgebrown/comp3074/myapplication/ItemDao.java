package ca.georgebrown.comp3074.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);

    @Query("DELETE FROM items")
    void deleteAll();

    @Query("DELETE FROM items WHERE item=:item")
    void deleteItem(String item);

    @Query("SELECT * FROM items ORDER BY item ASC")
    LiveData<List<Item>> getAll();
}
