package com.example.catalogoproductos.com.example.catalogoproductos.persistence;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "productos")
public class Producto implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "nombre")
    private String nomProducto;

    @ColumnInfo(name = "precio")
    private double precio;

    @ColumnInfo(name = "stock")
    private int stock;

    @ColumnInfo(name = "categoria")
    private String categoria;

    public Producto(String nomProducto, double precio, int stock, String categoria) {
        this.nomProducto = nomProducto;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    @Ignore
    public Producto(int id, String nomProducto, double precio, int stock, String categoria) {
        this.id = id;
        this.nomProducto = nomProducto;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    protected Producto(Parcel in) {
        id = in.readInt();
        nomProducto = in.readString();
        precio = in.readDouble();
        stock = in.readInt();
        categoria = in.readString();
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomProducto);
        dest.writeDouble(precio);
        dest.writeInt(stock);
        dest.writeString(categoria);
    }



}
