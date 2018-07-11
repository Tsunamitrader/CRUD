package com.example.fraga.exsqlite_09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fraga.exsqlite_09.Controller.Livro;
import com.example.fraga.exsqlite_09.Model.DaoLivro;

public class MainActivity extends AppCompatActivity {

    EditText edtTitulo, edtAutor, edtEditora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTitulo = findViewById(R.id.edtTitulo);
        edtAutor = findViewById(R.id.edtAutor);
        edtEditora = findViewById(R.id.edtEditora);
    }

    public void salvarLivro(View v){

        //populando o objeto do Controller: Livro.java
        Livro l = new Livro();
        l.setAutor(edtAutor.getText().toString());
        l.setTitulo(edtTitulo.getText().toString());
        l.setEditora(edtEditora.getText().toString());

        //mandando o objeto do Controller populado para a camada de persistencia
        DaoLivro livroDao = new DaoLivro(this);
        String resultado = livroDao.insereLivro(l);

        //printando um Toast
        Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();

    }

    public void listarLivros(View v){
        Intent it = new Intent(this, ListarActivity.class);
        startActivity(it);
    }




}
