package com.example.minhasreceitas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String NOME_BASE = "MinhasReceitas";
    private static final int VERSAO_BASE = 6;

    // Contacts table name
    private static final String TABLE_CONTACTS = "receita";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DbHelper(Context context) {
        super(context, NOME_BASE, null, VERSAO_BASE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTabelaLivro = "CREATE TABLE receita("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "titulo TEXT,"
                + "ingredientes TEXT,"
                + "modoDepreparo TEXT,"
                + "image BLOB"
                + ")";
        db.execSQL(sqlCreateTabelaLivro);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlDropTabelaLivros = "DROP TABLE receita";
        db.execSQL(sqlDropTabelaLivros);

        onCreate(db);
    }


    public void insertLivro(Receita receita) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();


        cv.put("titulo", receita.getTitulo());
        cv.put("ingredientes", receita.getIngredientes());
        cv.put("modoDepreparo", receita.getModoDepreparo());

        cv.put(KEY_IMAGE, receita._image); // Contact Phone
        db.insert("receita", null, cv);

        db.close();
    }


    public List<Receita> selectTodosOsLivros() {

        List<Receita> listLivros = new ArrayList<Receita>();

        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectTodosLivros = "SELECT * FROM receita";

        Cursor c = db.rawQuery(sqlSelectTodosLivros, null);

        if (c.moveToFirst()) {
            do {
                Receita receita = new Receita();
                receita.setId(c.getInt(0));
                receita.setTitulo(c.getString(1));
                receita.setIngredientes(c.getString(2));
                receita.setModoDepreparo(c.getString(3));

                listLivros.add(receita);
            } while (c.moveToNext());
        }

        db.close();
        return listLivros;
    }

    public Receita selectUmLivro(String titulo) { // Recebe por parâmetros o título selecionado no List View, e retorna um ArrayList de Receita


        Receita lib = null;
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectTodosLivros = "SELECT * FROM receita WHERE titulo = '" + titulo + "'";

        Cursor c = db.rawQuery(sqlSelectTodosLivros, null);

        if (c.moveToFirst()) {
            do {
                lib = new Receita();
                lib.setId(c.getInt(0));
                lib.setTitulo(c.getString(1));
                lib.setIngredientes(c.getString(2));
                lib.setModoDepreparo(c.getString(3));
                //	listLivros.add(livro); // Add os atributos do livro na ArrayList (listLivros)
            } while (c.moveToNext());
        }
        db.close(); // Fecha a conexão

        return lib;
    }

    public void apagarUmLivro(String titulo) { // Recebe por parâmetros o título selecionado no List View, e retorna um ArrayList de Receita


        Receita lib = null;
        SQLiteDatabase db = getReadableDatabase();

        String sqlDropTabelaLivros = "DELETE FROM receita WHERE titulo = '" + titulo + "'";

        db.execSQL(sqlDropTabelaLivros);

    }


    public void editarUmLivro(Receita receita, String titulo) { // Recebe por parâmetros o título selecionado no List View, e retorna um ArrayList de Receita
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("titulo", receita.getTitulo());
        cv.put("ingredientes", receita.getIngredientes());
        cv.put("modoDepreparo", receita.getModoDepreparo());
        db.update("receita", cv, "titulo = '" + titulo + "'", null);
        db.close();
    }


    protected void exportDB() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//" + "com.example.minhabiblioteca"
                        + "//databases//" + "MinhasReceitas";
                String backupDBPath = "MinhasReceitas";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                //	Toast.makeText(getApplicationContext(), "Backup Successful!",
                //		Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {

            //	Toast.makeText(getApplicationContext(), "Backup Failed!", Toast.LENGTH_SHORT)
            //	.show();

        }
    }


    protected void importDB() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            if (sd.canWrite()) {
                String currentDBPath = "//data//" + "com.example.minhabiblioteca"
                        + "//databases//" + "MinhasReceitas";
                String backupDBPath = "MinhasReceitas"; // From SD directory.
                File backupDB = new File(data, currentDBPath);
                File currentDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                //	Toast.makeText(getApplicationContext(), "Import Successful!",
                //		Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {

            //	Toast.makeText(getApplicationContext(), "Import Failed!", Toast.LENGTH_SHORT)
            //		.show();

        }
    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */


    public// Adding new contact
    void addContact(Receita receita) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();


        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE, receita._image); // Contact Phone
        values.put("titulo", "qq"); // Contact Phone ingredientes
        values.put("ingredientes", "ds"); // Contact Phone modoDepreparo
        values.put("modoDepreparo", "ds"); // Contact Phone modoDepreparo


        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }


    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM receita ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setImage(cursor.getBlob(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
        return contactList;

    }

    // Getting All Contacts
    public List<Contact> pegarum(String titulo) {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM receita WHERE titulo = '" + titulo + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setImage(cursor.getBlob(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
        return contactList;

    }

    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE, contact.getImage());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});

    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}
