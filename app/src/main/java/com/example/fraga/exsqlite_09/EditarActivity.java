package com.example.fraga.exsqlite_09;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fraga.exsqlite_09.Controller.Livro;
import com.example.fraga.exsqlite_09.Model.CriaBanco;
import com.example.fraga.exsqlite_09.Model.DaoLivro;

public class EditarActivity extends AppCompatActivity {

    EditText edtId, edtEditora, edtAutor, edtTitulo;
    Cursor livroCursor;
    DaoLivro livroDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        edtId = findViewById(R.id.edtId);
        edtAutor = findViewById(R.id.edtAutorEdt);
        edtEditora = findViewById(R.id.edtEditoraEdt);
        edtTitulo = findViewById(R.id.edtTituloEdt);

        Intent it = getIntent();
        String codigo = this.getIntent().getStringExtra("codigo");
        livroDao = new DaoLivro(this);
        livroCursor = livroDao.buscaPorId(Integer.parseInt(codigo));

        edtId.setEnabled(false);
        edtId.setText(livroCursor.getString(livroCursor.getColumnIndexOrThrow(CriaBanco.ID)));
        edtTitulo.setText(livroCursor.getString(livroCursor.getColumnIndexOrThrow(CriaBanco.TITULO)));
        edtEditora.setText(livroCursor.getString(livroCursor.getColumnIndexOrThrow(CriaBanco.EDITORA)));
        edtAutor.setText(livroCursor.getString(livroCursor.getColumnIndexOrThrow(CriaBanco.AUTOR)));
    }

    public void alterar(View v){

        Livro livroAlterado = new Livro();

        livroAlterado.setId(Integer.parseInt(edtId.getText().toString()));
        livroAlterado.setTitulo(edtTitulo.getText().toString());
        livroAlterado.setEditora(edtEditora.getText().toString());
        livroAlterado.setAutor(edtAutor.getText().toString());

        //passando o objeto alterado para a camada de persistencia
        livroDao.alterarRegistro(livroAlterado);
        Intent it = new Intent(this, ListarActivity.class);
        startActivity(it);
    }

    public void deletar(View v){

        livroDao.deletarRegistro(Integer.parseInt(edtId.getText().toString()));
        Intent it = new Intent(this, ListarActivity.class);
        startActivity(it);
    }




}
