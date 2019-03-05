package com.example.catalogoproductos;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.catalogoproductos.com.example.catalogoproductos.persistence.Producto;
import com.example.catalogoproductos.com.example.catalogoproductos.persistence.ProductoViewModel;

public class Modificar extends AppCompatActivity {

    private EditText nombre, precio, stock;
    private int id;
    private Spinner categoria;
    private Button modificar;
    private Context context;
    private Producto producto;
    private ProductoViewModel mProductoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        context = this;
        nombre = findViewById(R.id.nombre2);
        precio = findViewById(R.id.precio2);
        stock = findViewById(R.id.stock2);
        categoria = findViewById(R.id.categoria2);
        modificar = findViewById(R.id.modificar);
        mProductoViewModel = ViewModelProviders.of(this).get(ProductoViewModel.class);

        producto = getIntent().getParcelableExtra("producto");
        nombre.setText(producto.getNomProducto());
        precio.setText(String.valueOf(producto.getPrecio()));
        stock.setText(String.valueOf(producto.getStock()));
        spinner();
        funciones();
    }

    private void spinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context, R.array.items,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria.setAdapter(adapter);
        int position = adapter.getPosition(producto.getCategoria());
        categoria.setSelection(position);
    }

    private void funciones(){
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                producto.setNomProducto(nombre.getText().toString());
                producto.setPrecio(Double.valueOf(precio.getText().toString()));
                producto.setStock(Integer.valueOf(stock.getText().toString()));
                producto.setCategoria(categoria.getSelectedItem().toString());
                mProductoViewModel.modificar(producto);
                finish();
                Toast t1 = Toast.makeText(context,"Producto modificado",Toast.LENGTH_SHORT);
                t1.show();
            }
        });
    }

}
