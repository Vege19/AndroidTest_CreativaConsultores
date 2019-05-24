package com.example.kevin.androidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertProductActivity extends AppCompatActivity {

    private EditText insertName, insertPrice, insertQuantity;
    private Button insertProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);

        //intancia de vistas
        insertName = findViewById(R.id.inputProductName);
        insertPrice = findViewById(R.id.inputProductPrice);
        insertProduct = findViewById(R.id.insertProductButton);
        insertQuantity = findViewById(R.id.inputProductQuantity);
        insertQuantity.setText(String.valueOf(1));

        //listener para el botton
        insertProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtenemos la cantidad de productos que el usuario desea
                int quantity = Integer.parseInt(insertQuantity.getText().toString().trim());

                for (int i = 0; i < quantity; i++) {
                    insertProduct();
                    //finalmente se cierra la actividad
                    InsertProductActivity.this.finish();
                }
            }
        });

    }

    //metodo para agregar el producto
    private void insertProduct() {
        //obtenemos los datos que escribio el usuario
        String name = insertName.getText().toString().trim();
        double price = Double.parseDouble(insertPrice.getText().toString().trim());

        //validamos el formulario
        if (name.isEmpty()) {
            insertName.setError("Este campo no puede estar vacío");
            insertName.requestFocus();
            return;
        }

        if (insertPrice.equals("")) {
            insertPrice.setError("Ingrese un precio válido");
            insertPrice.requestFocus();
            return;
        }

        //una vez validados los datos, insertamos el producto del usuario
        //creamos el producto hecho por el usuario
        Product product = new Product(name, price);
        MainActivity.dataBaseBuild().productDao().insertProduct(product);

    }
}
