package com.example.catalogoproductos.com.example.catalogoproductos.persistence;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ProductoViewModel extends AndroidViewModel {

    private ProductoRepository mRepository;

    private LiveData<List<Producto>> mAllProductos;

    public ProductoViewModel(Application application){
        super(application);
        mRepository = new ProductoRepository(application);
        mAllProductos = mRepository.getAllProducts();
    }

    public LiveData<List<Producto>> getAllProductos() {
        return  mAllProductos;
    }

    public void insert(Producto producto) { mRepository.insert(producto);}

    public void modificar(Producto p){
        mRepository.modificar(p);
    }

    public void eliminar(Producto producto){ mRepository.eliminar(producto); }


}
