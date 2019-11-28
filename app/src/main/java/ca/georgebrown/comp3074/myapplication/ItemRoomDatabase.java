package ca.georgebrown.comp3074.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class ItemRoomDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();

    private static volatile ItemRoomDatabase INSTANCE = null;

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        public ItemDao dao;

        PopulateDbAsync(ItemRoomDatabase db) {
            this.dao = db.itemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.getAll();
            return null;
        }
    }

    static ItemRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (ItemRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ItemRoomDatabase.class,
                            "item_database"
                    ).addCallback(callback).build();
                }
            }
        }
        return INSTANCE;
    }
}
