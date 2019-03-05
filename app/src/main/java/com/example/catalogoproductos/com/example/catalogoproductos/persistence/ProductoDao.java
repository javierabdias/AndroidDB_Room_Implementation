package com.example.catalogoproductos.com.example.catalogoproductos.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.catalogoproductos.com.example.catalogoproductos.persistence.Producto;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProductoDao {

    @Insert(onConflict = REPLACE)
    void insert(Producto producto);

    @Delete
    void eliminar(Producto producto);

    @Update
    void modificar(Producto producto);

    @Query("DELETE FROM productos")
    void deleteAll();

    @Query("SELECT * from productos")
    LiveData<List<Producto>> getAllProducts();

}
