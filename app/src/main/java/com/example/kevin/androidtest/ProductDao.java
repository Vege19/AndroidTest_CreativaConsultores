package com.example.kevin.androidtest;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface ProductDao {

    //metodos para crud de la base de datos

    //Obtener todos los productos
    @Query("SELECT * FROM product")
    List<Product> getProducts();

    //Insertar un producto
    @Insert
    void insertProduct(Product... products);

    //Editar un producto
    @Query("UPDATE product SET product_name = :name, product_price = :price WHERE id = :id")
    void updateProduct(String name, double price, int id);

}
