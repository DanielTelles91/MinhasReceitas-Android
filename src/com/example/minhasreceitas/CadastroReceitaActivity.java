package com.example.minhasreceitas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.minhabiblioteca.R;

import java.io.ByteArrayOutputStream;

public class CadastroReceitaActivity extends Activity implements OnClickListener {

    EditText etTitulo;
    EditText etIngredientes;
    EditText etModoDePreparo;
    Button addImage;
    private static final int CAMERA_REQUEST = 1;
    private static final int PICK_FROM_GALLERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_cadastro_livros);

        etTitulo = (EditText) findViewById(R.id.etTitulo);
        etIngredientes = (EditText) findViewById(R.id.etIngredientes);
        etModoDePreparo = (EditText) findViewById(R.id.etModoDePreparo);


        final String[] option = new String[]{"Tirar Foto",
                "Selecionar na Galeria"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, option);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Selecione uma Opção");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Log.e("Selected Item", String.valueOf(which));
                if (which == 0) {
                    callCamera();
                }
                if (which == 1) {
                    callGallery();
                }

            }
        });
        final AlertDialog dialog = builder.create();

        addImage = (Button) findViewById(R.id.addfoto);

        addImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Receita receita = new Receita();

        receita.setTitulo(etTitulo.getText().toString());
        receita.setIngredientes(etIngredientes.getText().toString());
        receita.setModoDepreparo(etModoDePreparo.getText().toString());

        DbHelper dbHelper = new DbHelper(this);
        dbHelper.insertLivro(receita);
        //chama a página para adicionar a foto

        Intent it = new Intent(CadastroReceitaActivity.this, SQLiteDemoActivity.class); // Chama a classe MostrarUmaReceitaActivity

        it.putExtra("titulo", receita.getTitulo()); // Passa por parâmetro o Título do livro selecionado
        startActivity(it);
        finish();
    }

    /**
     * abri o método câmera
     */
    public void callCamera() {
        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra("crop", "true");
        cameraIntent.putExtra("aspectX", 0);
        cameraIntent.putExtra("aspectY", 0);
        cameraIntent.putExtra("outputX", 200);
        cameraIntent.putExtra("outputY", 150);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    /**
     * abri a galeria de fotos
     */

    public void callGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(
                Intent.createChooser(intent, "Complete action using"),
                PICK_FROM_GALLERY);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;

        Intent intent = getIntent();
        Bundle infos = intent.getExtras();
        String mostraTexto = null;
        if (infos != null) {
            mostraTexto = infos.getString("titulo"); // Joga o Título do livro na variável mostraTexto
        }
        switch (requestCode) {
            case CAMERA_REQUEST:

                Bundle extras = data.getExtras();

                if (extras != null) {
                    Bitmap yourImage = extras.getParcelable("data");
                    // convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte imageInByte[] = stream.toByteArray();
                    //Log.e("output before conversion", imageInByte.toString());
                    // Inserting Contacts
                    Log.d("Insert: ", "Inserting ..");
                    new Receita(imageInByte);
                    Receita receita = new Receita();
                    receita.setImage(imageInByte);
                    receita.setTitulo(etTitulo.getText().toString());
                    receita.setIngredientes(etIngredientes.getText().toString());
                    receita.setModoDepreparo(etModoDePreparo.getText().toString());
                    DbHelper dbHelper = new DbHelper(this);
                    dbHelper.insertLivro(receita);

                    Intent it = new Intent(CadastroReceitaActivity.this, MostraTodasAsReceitasActivity.class); // Chama a classe MostrarUmaReceitaActivity
                    startActivity(it); // retorna para a página inicial
                    finish();
                }
                break;
            case PICK_FROM_GALLERY:
                Bundle extras2 = data.getExtras();
                if (extras2 != null) {
                    Bitmap yourImage = extras2.getParcelable("data");
                    // convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte imageInByte[] = stream.toByteArray();
                    //Log.e("output before conversion", imageInByte.toString());
                    // Inserting Contacts
                    Log.d("Insert: ", "Inserting ..");
                    new Receita(imageInByte);
                    Receita receita = new Receita();
                    receita.setImage(imageInByte);
                    receita.setTitulo(etTitulo.getText().toString());
                    receita.setIngredientes(etIngredientes.getText().toString());
                    receita.setModoDepreparo(etModoDePreparo.getText().toString());
                    DbHelper dbHelper = new DbHelper(this);
                    dbHelper.insertLivro(receita);

                    Intent it = new Intent(CadastroReceitaActivity.this, MostraTodasAsReceitasActivity.class); // Chama a classe MostrarUmaReceitaActivity
                    startActivity(it); // retorna para a página inicial
                    finish();
                }
                break;
        }
    }
}
