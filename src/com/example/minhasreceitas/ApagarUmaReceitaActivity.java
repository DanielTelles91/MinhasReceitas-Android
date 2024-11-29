package com.example.minhasreceitas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by DANIEL on 09/10/2015.
 */
public class ApagarUmaReceitaActivity extends Activity{

    String teste ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle infos = intent.getExtras();
        if(infos!=null)
        {
            teste = infos.getString("infos"); // Joga o Título do livro na variável mostraTexto
        }
        DbHelper dbHelper = new DbHelper(this);
        dbHelper.apagarUmLivro(teste); // apaga
        Toast.makeText(getBaseContext(),"Receita " + teste + ", apagado com sucesso!!!" , Toast.LENGTH_LONG).show(); // exibi o balao
        Intent it = new Intent(ApagarUmaReceitaActivity.this, MostraTodasAsReceitasActivity.class); // apos apagar chama o listar todos os livros
        startActivity(it);
    }
}
