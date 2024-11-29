package com.example.minhasreceitas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.minhabiblioteca.R;

/**
 * Created by DANIEL on 23/10/2015.
 */
public class ConfigSistema extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_sistema);
        final DbHelper dbHelper = new DbHelper(this);

        Button voltar = (Button) findViewById(R.id.voltar);
        Button btAbreEditar = (Button) findViewById(R.id.btAbreEditar);//Faz o backup
        Button btAbreRestaurar = (Button) findViewById(R.id.btAbreRestaurar); // faz a restauração do sistema
        voltar.setOnClickListener(this);


        btAbreEditar.setOnClickListener(new OnClickListener() {// Faz o backup
            @Override
            public void onClick(View v) {
                dbHelper.exportDB();
                Toast.makeText(getBaseContext(), "Backup feito com sucesso na raiz sdcard!", Toast.LENGTH_LONG).show(); // exibi o balao
            }
        });


        btAbreRestaurar.setOnClickListener(new OnClickListener() {// Botão responsável pela restauração do sistema
            @Override
            public void onClick(View v) {
                dbHelper.importDB();
                Toast.makeText(getBaseContext(), "Restauração feita com sucesso!", Toast.LENGTH_LONG).show(); // exibi o balao
                Intent it = new Intent(ConfigSistema.this, MostraTodasAsReceitasActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(ConfigSistema.this, MostraTodasAsReceitasActivity.class);
        startActivity(it);
    }

}
