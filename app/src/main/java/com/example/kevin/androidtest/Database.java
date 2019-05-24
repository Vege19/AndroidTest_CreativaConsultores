package com.example.kevin.androidtest;

import android.arch.persistence.room.RoomDatabase;

//Construccion de la base de datos
@android.arch.persistence.room.Database(entities = {Product.class}, version = 1)
public abstract class Database extends RoomDatabase {

    //Con esta instancia se manejan las acciones de la base de datos como insert, delete, etc
    public abstract ProductDao productDao();


}