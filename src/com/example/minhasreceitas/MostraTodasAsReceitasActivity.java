package com.example.minhasreceitas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minhabiblioteca.R;

import java.util.ArrayList;
import java.util.List;


public class MostraTodasAsReceitasActivity extends Activity {

    private ListView lvMostraTodosOsLivros;
    ListView dataList;
    byte[] imageName;
    int imageId;
    Bitmap theImage;
    DbHelper db;
    ArrayList<Contact> imageArry = new ArrayList<Contact>();
    ContactImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_mostra_todos_livros);

        final DbHelper dbHelper = new DbHelper(this);

        //   Button btAbreCadastro = (Button) findViewById(R.id.btAbreCadastro);


        //   Button teste = (Button) findViewById(R.id.teste);//


        lvMostraTodosOsLivros = (ListView) findViewById(R.id.lvMostraTodosOsLivros);


// Getting a reference to listview of main.xml layout file
        ListView listView = (ListView) findViewById(R.id.lvMostraTodosOsLivros); // ID do ListView

        OnItemClickListener itemClickListener = new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);

                String item = ((TextView) view).getText().toString();

                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show(); // exibi o balao
                Intent it = new Intent(MostraTodasAsReceitasActivity.this, MostrarUmaReceitaActivity.class); // Chama a classe MostrarUmaReceitaActivity


                it.putExtra("infos", item); // Passa por parâmetro o Título do livro selecionado
                startActivity(it);
            }

        };


// Setting the item click listener for the listview
        listView.setOnItemClickListener(itemClickListener);


        dataList = (ListView) findViewById(R.id.lvMostraTodosOsLivros);
        /**
         * create DatabaseHandler object
         */
        db = new DbHelper(this);
        /**
         * Reading and getting all records from database
         */
        List<Contact> contacts = db.pegarum("ds");
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

    public boolean onOptionsItemSelected(MenuItem item) {
        //Referente ao menu da parte superior.
        switch (item.getItemId()) {
            case R.id.adicionar:
                Toast.makeText(getBaseContext(), "Cadastrar uma nova receita", Toast.LENGTH_LONG).show(); // exibi o balao
                Intent it = new Intent(MostraTodasAsReceitasActivity.this, CadastroReceitaActivity.class);
                startActivity(it);
                return true;

            case R.id.configuracao:
                Toast.makeText(getBaseContext(), "Configurações do Sistema", Toast.LENGTH_LONG).show(); // exibi o balao
                Intent it3 = new Intent(MostraTodasAsReceitasActivity.this, ConfigSistema.class);
                startActivity(it3);
                return true;

            case R.id.sobre:
                Toast.makeText(getBaseContext(), "Sobre Minhas Receitas", Toast.LENGTH_LONG).show(); // exibi o balao
                Intent it2 = new Intent(MostraTodasAsReceitasActivity.this, Sobre.class);
                startActivity(it2);
                return true;

            case R.id.sair:
                Toast.makeText(getBaseContext(), "Saindo", Toast.LENGTH_LONG).show(); // exibi o balao
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // cria o menu superior
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() { // Chama o método responsável por buscar todos os livros no banco de dados e joga na tela inicial
        super.onResume();

        DbHelper dbHelper = new DbHelper(this);
        List<Receita> listaLivros = dbHelper.selectTodosOsLivros();

        ArrayAdapter<Receita> adp = new ArrayAdapter<Receita>(this, android.R.layout.simple_list_item_1, listaLivros);

        lvMostraTodosOsLivros.setAdapter(adp);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    } // desabilita o botao voltar e finaliza a aplicação
}
