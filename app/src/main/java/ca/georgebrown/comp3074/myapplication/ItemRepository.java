package ca.georgebrown.comp3074.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {
    private ItemDao mItemDao;
    private LiveData<List<Item>> mAllItems;

    ItemRepository(Application application){
        ItemRoomDatabase db = ItemRoomDatabase.getDatabase(application);
        mItemDao = db.itemDao();
        mAllItems = mItemDao.getAll();
    }

    LiveData<List<Item>> getAllItems(){
        return mAllItems;
    }

    void insert(Item item){
        new InsertAsyc(mItemDao).execute(item);
    }

    void deleteItem(String item) { new DeleteAsyc(mItemDao).execute(item); }

    private static class InsertAsyc extends AsyncTask<Item, Void, Void> {

        private ItemDao dao;

        InsertAsyc(ItemDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            dao.insert(items[0]);
            return null;
        }
    }
    private static class DeleteAsyc extends AsyncTask<String, Void, Void> {

        private ItemDao dao;

        DeleteAsyc(ItemDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(String... items) {
            dao.deleteItem(items[0]);
            return null;
        }
    }
}
