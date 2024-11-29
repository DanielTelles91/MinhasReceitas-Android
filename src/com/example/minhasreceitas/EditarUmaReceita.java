package com.example.minhasreceitas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minhabiblioteca.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANIEL on 11/10/2015.
 */
public class EditarUmaReceita extends Activity {

    private String mostraTexto;
    EditText etTitulo;
    EditText etIngredientes;
    EditText etModoDePreparo;

    DbHelper dbHelper = new DbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_livro);

        etTitulo = (EditText) findViewById(R.id.etTitulo);
        etIngredientes  = (EditText) findViewById(R.id.etIngredientes);
        etModoDePreparo  = (EditText) findViewById(R.id.etModoDePreparo);

        Button btSalvar = (Button) findViewById(R.id.btSalvar);//

        btSalvar.setOnClickListener(new View.OnClickListener() {// Chama a ApagarUmaReceitaActivity
            @Override
            public void onClick(View v) {

                Receita receita = new Receita();

                receita.setTitulo(etTitulo.getText().toString());
                receita.setIngredientes(etIngredientes.getText().toString());
                receita.setModoDepreparo(etModoDePreparo.getText().toString());


                dbHelper.editarUmLivro(receita, mostraTexto);
                Toast.makeText(getBaseContext(), "Editado com Sucesso", Toast.LENGTH_LONG).show(); // exibi o balao
                Intent it = new Intent(EditarUmaReceita.this, MostraTodasAsReceitasActivity.class);

                startActivity(it);

            }
        });






    }




    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();

        Bundle infos = intent.getExtras();

        if(infos!=null)
        {
            mostraTexto = infos.getString("infos"); // Joga o Título do livro na variável mostraTexto

        }

        DbHelper dbHelper = new DbHelper(this);
        //   List<Receita> listaLivros = (List<Receita>) dbHelper.selectUmLivro(mostraTexto);
        Receita teste = new Receita();
        teste =  dbHelper.selectUmLivro(mostraTexto);

        List<ReceitaDetalhes> legendList= new ArrayList<ReceitaDetalhes>();

        legendList.add(new ReceitaDetalhes(1, teste.getTitulo(), teste.getIngredientes(), teste.getModoDepreparo()));


        //  ArrayAdapter<ReceitaDetalhes> adp = new ArrayAdapter<ReceitaDetalhes>(this, android.R.layout., legendList);

        // title.setAdapter(adp);

        EditText myAwesomeTextView = (EditText)findViewById(R.id.etIngredientes);
        EditText myAwesomeTextView2 = (EditText)findViewById(R.id.etTitulo);
        EditText myAwesomeTextView3 = (EditText)findViewById(R.id.etModoDePreparo);

        myAwesomeTextView2.setText(teste.getTitulo(),TextView.BufferType.EDITABLE);
        myAwesomeTextView.setText(teste.getIngredientes(),TextView.BufferType.EDITABLE);
        myAwesomeTextView3.setText(teste.getModoDepreparo(),TextView.BufferType.EDITABLE);


    }
}
