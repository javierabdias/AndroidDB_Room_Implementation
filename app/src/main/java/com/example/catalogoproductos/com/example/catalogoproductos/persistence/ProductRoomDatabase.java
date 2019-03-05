package com.example.catalogoproductos.com.example.catalogoproductos.persistence;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Producto.class}, version = 1)
public abstract class ProductRoomDatabase extends RoomDatabase {

    public abstract ProductoDao productoDao();

    private static volatile  ProductRoomDatabase INSTANCE;

    static ProductRoomDatabase getDatabase(final Context context){

        if(INSTANCE == null){
            synchronized (ProductRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductRoomDatabase.class,"productos").build();
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

        private final ProductoDao mDao;

        PopulateDbAsync(ProductRoomDatabase db) {
            mDao = db.productoDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Producto producto = new Producto("Leche",12.50,12,"Cremeria");
            mDao.insert(producto);
            return null;
        }
    }
}
