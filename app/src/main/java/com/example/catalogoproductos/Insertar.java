package com.example.catalogoproductos;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.catalogoproductos.com.example.catalogoproductos.persistence.Producto;
import com.example.catalogoproductos.com.example.catalogoproductos.persistence.ProductoRepository;
import com.example.catalogoproductos.com.example.catalogoproductos.persistence.ProductoViewModel;

public class Insertar extends AppCompatActivity {

    private EditText nombre, precio, stock;
    private Spinner categoria;
    private Button añadir;
    private Context context;
    private ProductoViewModel mProductoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        mProductoViewModel = ViewModelProviders.of(this).get(ProductoViewModel.class);
        nombre = findViewById(R.id.nombre);
        precio = findViewById(R.id.precio);
        stock = findViewById(R.id.stock);
        categoria = findViewById(R.id.categoria);
        añadir = findViewById(R.id.añadir);
        context = this;
        spinner();
        guardar();
    }

    private void guardar(){

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto producto = new Producto(nombre.getText().toString(),
                        Double.valueOf(precio.getText().toString()),
                        Integer.valueOf(stock.getText().toString()),
                        categoria.getSelectedItem().toString());

                mProductoViewModel.insert(producto);

                nombre.setText("");
                precio.setText("");
                stock.setText("");

                Toast t1 = Toast.makeText(context,"Producto añadido",Toast.LENGTH_SHORT);
                t1.show();
            }
        });

    }

    private void spinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context, R.array.items,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria.setAdapter(adapter);
    }
}
