package com.example.fraga.exsqlite_09;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fraga.exsqlite_09.Model.DaoLivro;

public class ListarActivity extends AppCompatActivity {


    ListView lst01;
    TextView txtID, txtTitulo, txtAutor, txtEditora;
    private DaoLivro livroDao;
    private Cursor livroCursor;
    private CursorAdapter adapter;
    private static final String CAMPOS[] = {"titulo", "autor", "editora", "_id"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        lst01 = findViewById(R.id.lst01);

        livroDao = new DaoLivro(this);
        livroCursor = livroDao.listarLivros();

        if (livroCursor.getCount() > 0){
            adapter = new SimpleCursorAdapter(this, R.layout.activity_listar, livroCursor, CAMPOS, new int[]{R.id.txtID, R.id.txtTitulo, R.id.txtAutor, R.id.txtEditora}, 1);
            lst01.setAdapter(adapter);
            lst01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    livroCursor.moveToPosition(position);
                    String codigo = livroCursor.getString(livroCursor.getColumnIndexOrThrow("_id"));

                    Intent it = new Intent(ListarActivity.this, EditarActivity.class);
                    it.putExtra("codigo", codigo);
                    startActivity(it);
                    finish();
                }
            });
        }
        else
            Toast.makeText(this, "Nenhum registro encontrado", Toast.LENGTH_LONG).show();

    }
}
