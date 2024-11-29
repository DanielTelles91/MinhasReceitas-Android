package com.example.minhasreceitas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.minhabiblioteca.R;


/**
 * Created by DANIEL on 12/10/2015.
 */
public class Sobre extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sobre);


        Button voltar = (Button) findViewById(R.id.voltar);

        voltar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent it = new Intent(Sobre.this, MostraTodasAsReceitasActivity.class);
        startActivity(it);
    }
}
