package com.example.catalogoproductos;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.catalogoproductos.com.example.catalogoproductos.persistence.Producto;
import com.example.catalogoproductos.com.example.catalogoproductos.persistence.ProductoViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductoViewModel mProductoViewModel;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProductoViewModel = ViewModelProviders.of(this).get(ProductoViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.rv);
        final ProductoListAdapter adapter = new ProductoListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProductoViewModel.getAllProductos().observe(this, new Observer<List<Producto>>() {
            @Override
            public void onChanged(@Nullable final List<Producto> productos) {
                adapter.setProductos(productos);
            }
        });




        context = this;
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Producto producto = adapter.id(position);
                        Toast.makeText(MainActivity.this, "Producto eliminado: " +
                                producto.getNomProducto(), Toast.LENGTH_LONG).show();
                        mProductoViewModel.eliminar(producto);
                    }
                });
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.agregar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nuevo:
                Intent i = new Intent(this,Insertar.class);
                startActivity(i);
                return true;
            default: return false;
        }

    }


}
