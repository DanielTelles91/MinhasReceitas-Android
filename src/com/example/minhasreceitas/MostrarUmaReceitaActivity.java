package com.example.minhasreceitas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minhabiblioteca.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANIEL on 08/10/2015.
 */
public class MostrarUmaReceitaActivity extends Activity {

    private TextView title;
    private String mostraTexto;
    byte[] imageName;
    int imageId;
    Bitmap theImage;
    DbHelper db;
    ArrayList<Contact> imageArry = new ArrayList<Contact>();
    ContactImageAdapter imageAdapter;
    ListView dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_livro);
        title = (TextView) findViewById(R.id.titulo);

        dataList = (ListView) findViewById(R.id.lvMostraTodosOsLivros);

        Intent intent = getIntent();

        Bundle infos = intent.getExtras();

        if (infos != null) {
            mostraTexto = infos.getString("infos"); // Joga o Título do livro na variável mostraTexto

        }
        /**
         * create DatabaseHandler object
         */
        db = new DbHelper(this);
        /**
         * Reading and getting all records from database
         */
        List<Contact> contacts = db.pegarum(mostraTexto);
        for (Contact cn : contacts) {
            String log = "ID:" + cn.getID()
                    + " ,Image: " + cn.getImage();

            // Writing Contacts to log
            Log.d("Result: ", log);
            // add contacts data in arrayList
            imageArry.add(cn);

        }
        /**
         * Set Data base Item into listview
         */
        imageAdapter = new ContactImageAdapter(this, R.layout.screen_list,
                imageArry);
        dataList.setAdapter(imageAdapter);


    }


    @Override
    protected void onResume() {
        super.onResume();


        DbHelper dbHelper = new DbHelper(this);

        Receita teste = new Receita();
        teste = dbHelper.selectUmLivro(mostraTexto);

        List<ReceitaDetalhes> legendList = new ArrayList<ReceitaDetalhes>();

        legendList.add(new ReceitaDetalhes(1, teste.getTitulo(), teste.getIngredientes(), teste.getModoDepreparo()));

        TextView myAwesomeTextView = (TextView) findViewById(R.id.ingredientes);
        TextView myAwesomeTextView2 = (TextView) findViewById(R.id.titulo);
        TextView myAwesomeTextView3 = (TextView) findViewById(R.id.modoDePreparo);

        myAwesomeTextView2.setText(teste.getTitulo());
        myAwesomeTextView.setText(teste.getIngredientes());
        myAwesomeTextView3.setText(teste.getModoDepreparo());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Chama a barra personalizada
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu_detalhes_receita, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.voltar:
                Toast.makeText(getBaseContext(), "Menu Principal", Toast.LENGTH_LONG).show(); // exibi o balao
                Intent it = new Intent(MostrarUmaReceitaActivity.this, MostraTodasAsReceitasActivity.class);
                startActivity(it);
                return true;

            case R.id.editar:
                Intent it2 = new Intent(MostrarUmaReceitaActivity.this, EditarUmaReceita.class);
                it2.putExtra("infos", mostraTexto); // Passa por parâmetro o Título do livro selecionado
                startActivity(it2);
                return true;

            case R.id.excluir:
                Intent it3 = new Intent(MostrarUmaReceitaActivity.this, ApagarUmaReceitaActivity.class);
                it3.putExtra("infos", mostraTexto); // Passa por parâmetro o Título do livro selecionado
                startActivity(it3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
