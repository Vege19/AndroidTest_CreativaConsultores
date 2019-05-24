package com.example.kevin.androidtest;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Product> products;
    private FloatingActionButton mFab;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        //Instancia de las vistas
        mRecyclerView = findViewById(R.id.productsRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mFab = findViewById(R.id.addProductFAB);

        dataBaseBuild();

        //iniciamos la lista
        fillRecyclerView();

        //listener para el fab
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToInsert();
            }
        });


    }

    private void fillRecyclerView() {
        //llenamos nuestra lista con los datos de la db
        products = dataBaseBuild().productDao().getProducts();

        mRecyclerView.setAdapter(new ProductsAdapter(products, this));
    }


    //con este metodo podemos instanciar la base de datos
    public static Database dataBaseBuild() {
        //inicializamos la db
        Database product_db = Room.databaseBuilder(context, Database.class, "product_db").allowMainThreadQueries().build();

        return product_db;

    }

    //intent a insert
    private void intentToInsert() {
        Intent intent = new Intent(this, InsertProductActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        fillRecyclerView();
        super.onResume();

    }
}
