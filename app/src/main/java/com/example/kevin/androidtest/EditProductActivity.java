package com.example.kevin.androidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProductActivity extends AppCompatActivity {

    private EditText insertName, insertPrice;
    private Button updateProduct;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        //recuperamos los datos del item seleccionado
        product = (Product) getIntent().getSerializableExtra("product_data");

        //llenamos los edit text con los datos del item
        insertName = findViewById(R.id.updateProductName);
        insertName.setText(product.getName());
        insertPrice = findViewById(R.id.updateProductPrice);
        insertPrice.setText(String.valueOf(product.getPrice()));

        updateProduct = findViewById(R.id.updateProductButton);
        updateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct();
            }
        });

    }

    private void updateProduct() {
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


        MainActivity.dataBaseBuild().productDao().updateProduct(name, price, product.getId());

        this.finish();



    }
}
