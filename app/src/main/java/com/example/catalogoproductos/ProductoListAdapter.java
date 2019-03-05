package com.example.catalogoproductos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.catalogoproductos.com.example.catalogoproductos.persistence.Producto;

import java.util.List;

public class ProductoListAdapter extends RecyclerView.Adapter<ProductoListAdapter.ProductoViewHolder> {


    class ProductoViewHolder extends RecyclerView.ViewHolder {

        private final TextView nombreItemView;
        private final TextView idItemView;
        private final TextView precioItemView;
        private final TextView stockItemView;
        private final TextView categoriaItemView;
        private final TextView menuItemView;


        private ProductoViewHolder(View itemView){
            super(itemView);
            nombreItemView = itemView.findViewById(R.id.nombre);
            idItemView = itemView.findViewById(R.id.id);
            precioItemView = itemView.findViewById(R.id.precio);
            stockItemView = itemView.findViewById(R.id.stock);
            categoriaItemView = itemView.findViewById(R.id.categoria);
            menuItemView = itemView.findViewById(R.id.menu);

        }
    }

    private final LayoutInflater mInflater;
    private List<Producto> mProducto;
    private Context t;

    ProductoListAdapter(Context context){ mInflater = LayoutInflater.from(context);
         t = context;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.productos,parent,false);
        return new ProductoViewHolder(itemView);
    }

    public Producto id (int position){
        return mProducto.get(position);
    }

    @Override
    public void onBindViewHolder(final ProductoViewHolder holder, int position){
        if(mProducto != null){
            final Producto current = mProducto.get(position);
            holder.nombreItemView.setText(current.getNomProducto());
            holder.idItemView.setText(String.valueOf(current.getId()));
            holder.precioItemView.setText("$"+current.getPrecio()+"0");
            holder.categoriaItemView.setText(current.getCategoria());
            holder.stockItemView.setText(String.valueOf(current.getStock()));

            holder.menuItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(t, holder.menuItemView);
                    popupMenu.inflate(R.menu.opciones);

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()){

                                case R.id.modificar:
                                    Intent i = new Intent(t, Modificar.class);
                                    Producto producto = new Producto(
                                            current.getId(),
                                            current.getNomProducto(),
                                            current.getPrecio(),
                                            current.getStock(),
                                            current.getCategoria()
                                    );
                                    i.putExtra("producto", producto);
                                    t.startActivity(i);
                                    return true;
                            }
                            return false;
                        }
                    });

                    popupMenu.show();
                }
            });
        } else {
            holder.nombreItemView.setText("Sin productos");
        }
    }

    void setProductos(List<Producto>productos){
        mProducto = productos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mProducto != null)
            return mProducto.size();
        else return 0;
    }

}
