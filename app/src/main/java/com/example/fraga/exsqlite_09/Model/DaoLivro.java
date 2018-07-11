package com.example.fraga.exsqlite_09.Model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fraga.exsqlite_09.Controller.Livro;

//intermediario entre CriaBanco.java e Livro.java
public class DaoLivro {

    private SQLiteDatabase db;
    private CriaBanco banco;
    private static final String CAMPOS[] = {"titulo", "autor", "editora", "_id"};

    public DaoLivro(Context context){
        banco = new CriaBanco(context);
    }

    //insere dados do objeto Livro no bd
    public String insereLivro(Livro livro){

        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        long resultado;

        //populando objeto valores
        //valores.put(sql, objeto)
        valores.put(banco.TITULO, livro.getTitulo());
        valores.put(banco.EDITORA, livro.getEditora());
        valores.put(banco.AUTOR, livro.getAutor());

        resultado = db.insert(banco.TABELA, null, valores);
        db.close();

        if(resultado == -1)
            return "Erro ao inserir";
        else
            return "Inserido com sucesso";
    }

    public Cursor listarLivros(){

        db = banco.getWritableDatabase();

        Cursor livros = db.query(banco.TABELA, CAMPOS, null, null, null, null, null);

        if(livros != null)
            livros.moveToFirst();

        db.close();
        return livros;
    }

    public void alterarRegistro(Livro livro){

        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        String where = banco.ID + " = " + livro.getId();

        valores.put(banco.AUTOR, livro.getAutor());
        valores.put(banco.EDITORA, livro.getEditora());
        valores.put(banco.TITULO, livro.getTitulo());

        db.update(banco.TABELA, valores, where, null);
        db.close();
    }

    public void deletarRegistro(int id){

        String where = banco.ID + " = " + id;
        db = banco.getWritableDatabase();
        db.delete(banco.TABELA, where, null);
        db.close();
    }

    public Cursor buscaPorId(int id){

        String where = banco.ID + " = " + id;
        db = banco.getWritableDatabase();
        Cursor cursor = db.query(banco.TABELA, CAMPOS, where, null, null, null, banco.AUTOR);

        if(cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;

    }


}
