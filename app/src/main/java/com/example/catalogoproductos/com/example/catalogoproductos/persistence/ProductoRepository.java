package com.example.catalogoproductos.com.example.catalogoproductos.persistence;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ProductoRepository {

    private ProductoDao mProductDao;
    private LiveData<List<Producto>> mAllProducts;


    public ProductoRepository(Application application){
        ProductRoomDatabase db = ProductRoomDatabase.getDatabase(application);
        mProductDao = db.productoDao();
        mAllProducts = mProductDao.getAllProducts();
    }

    LiveData<List<Producto>> getAllProducts(){
        return  mAllProducts;
    }

    public void insert (Producto producto){
        new insertAsyncTask(mProductDao).execute(producto);
    }

    public void modificar(Producto p){
        new updateAsyncTask(mProductDao).execute(p);
    }

    public void eliminar(Producto producto) {
        new deleteAsyncTask(mProductDao).execute(producto);
    }




    private static class insertAsyncTask extends AsyncTask<Producto, Void, Void> {

        private ProductoDao mAsyncTaskDao;

        insertAsyncTask(ProductoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Producto... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Producto, Void, Void> {

        private ProductoDao mAsyncTaskDao;

        updateAsyncTask(ProductoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Producto... params) {
            mAsyncTaskDao.modificar(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Producto, Void, Void> {

        private ProductoDao mAsyncTaskDao;

        deleteAsyncTask(ProductoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Producto... params) {
            mAsyncTaskDao.eliminar(params[0]);
            return null;
        }
    }

}
