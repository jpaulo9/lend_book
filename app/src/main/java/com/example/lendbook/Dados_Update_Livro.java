package com.example.lendbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lendbook.banco.BaseDeDados;
import com.example.lendbook.objetos.Livro;
import com.example.lendbook.objetos.User;

public class Dados_Update_Livro extends AppCompatActivity {
    User usuario;
    EditText edtTitulo, edtAno, edtPag, edtEditora, edtEdicao, edtSinopse,
    ISBN, edtAutor;
    Button btnUp;
    TextView btnVot;
    BaseDeDados deDados;
    ImageView imgCapa;

    Bitmap imagem;
    Uri fileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados__update__livro);

        imgCapa = (ImageView) findViewById(R.id.imgLivroUp);
        edtTitulo = (EditText)findViewById(R.id.nomeLivroUp);
        edtAno = (EditText)findViewById(R.id.anoLivroUp);
        edtAutor = (EditText)findViewById(R.id.autorLivroUp);
        edtEdicao = (EditText)findViewById(R.id.edicaoLivroUp);
        edtEditora = (EditText)findViewById(R.id.editoraLivroUp);
        edtPag = (EditText)findViewById(R.id.pagLivroUp);
        edtSinopse = (EditText)findViewById(R.id.sinopseLivroUp);
        ISBN = (EditText)findViewById(R.id.ibsnLivroUp);

        btnUp = (Button) findViewById(R.id.btnUpLivro);
        btnVot = (TextView) findViewById(R.id.btn_volt_perfil);

        deDados = new BaseDeDados(Dados_Update_Livro.this);


        Bundle daddos = getIntent().getExtras();
        if ((daddos!= null) && (daddos.containsKey("User"))) {
            usuario = (User) daddos.getSerializable("User");

            Livro livro = deDados.SelecionarLivro(usuario.getIdLivro());
            if (livro!= null){
                edtTitulo.setHint("Título: "+livro.getNomeLivro());
                edtAutor.setHint("Autor: "+livro.getAutores());
                edtAno.setHint("Ano: "+livro.getAno());
                edtEdicao.setHint("Edição: "+livro.getEdicao());
                edtPag.setHint("Páginas: "+livro.getPaginas());
                edtEditora.setHint("Editora: "+livro.getEditora());
                edtSinopse.setHint("Sinopse: "+livro.getSinopse());
                ISBN.setHint("ISBN: "+livro.getIsbn());


            }else {

                Toast.makeText(Dados_Update_Livro.this, "Livro não encontrado", Toast.LENGTH_SHORT).show();
            }


        }else {
            Intent intent = new Intent(Dados_Update_Livro.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Livro livro = deDados.SelecionarLivro(usuario.getIdLivro());

                if (livro !=  null) UpdateDados(livro);
                else Toast.makeText(Dados_Update_Livro.this, "Livro não encontrado!", Toast.LENGTH_LONG).show();


            }
        });


        imgCapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TirarFoto();
            }
        });

        btnVot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent
                        = new Intent(Dados_Update_Livro.this, HomeApp.class);
                intent.putExtra("User", usuario);
                startActivity(intent);
            }
        });

    }


    public void TirarFoto(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == Dados_Update_Livro.RESULT_OK){

            Bundle extras = data.getExtras();
            fileImage = data.getData();
            imagem = (Bitmap) extras.get("data");
            imgCapa.setImageBitmap(imagem);

            UpdateFoto(imagem);
        }

        super.onActivityResult(requestCode, resultCode , data);
    }

    public void UpdateFoto(Bitmap bitmap){

        if (bitmap != null && imgCapa.getDrawable()!= null){

            Boolean result = deDados.upCapa(imagem, usuario.getIdLivro());

            if (result == true){
                Toast.makeText(Dados_Update_Livro.this, "Capa Atulizada!", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(Dados_Update_Livro.this, "Erro na ao atualizar capa!", Toast.LENGTH_SHORT).show();

        }
    }

    public void UpdateDados(Livro livro){

        Boolean validar = false;
        ValidarEditText validarEditText = new ValidarEditText();
        String tituloUp, anoUp, autorUp, pagUp, edicaoUp, isbnUp,
                editoraUp, upSinopse;

        Livro lvre = new Livro();


        if (validar = validarEditText.CamposText(edtTitulo.getText().toString())){
            tituloUp = livro.getNomeLivro();
            lvre.setNomeLivro(tituloUp);
        }
        else  {

            tituloUp = edtTitulo.getText().toString();
            lvre.setNomeLivro(tituloUp);

        }

        if (validar = validarEditText.CamposText(edtAutor.getText().toString())){
            autorUp = livro.getAutores();
            lvre.setAutores(autorUp);
        }else {
            autorUp = edtAutor.getText().toString();
            lvre.setAutores(autorUp);
        }

        if (validar = validarEditText.CamposText(edtAno.getText().toString())){
            anoUp = livro.getAno();
            lvre.setAno(anoUp);

        }else {
            anoUp = edtAno.getText().toString();
            lvre.setAno(anoUp);
        }

        if (validar = validarEditText.CamposText(edtPag.getText().toString())){
            pagUp = livro.getPaginas();
            lvre.setPaginas(pagUp);
        }else {
            pagUp = edtPag.getText().toString();
            lvre.setPaginas(pagUp);
        }

        if (validar = validarEditText.CamposText(edtEdicao.getText().toString())){
            edicaoUp = livro.getEdicao();
            lvre.setEdicao(edicaoUp);
        }else {
            edicaoUp = edtEdicao.getText().toString();
            lvre.setEdicao(edicaoUp);
        }

        if (validar = validarEditText.CamposText(edtEditora.getText().toString())){
            editoraUp = livro.getEditora();
            lvre.setEditora(editoraUp);
        }else{
            editoraUp = edtEditora.getText().toString();
            lvre.setEditora(editoraUp);
        }

        if (validar = validarEditText.CamposText(ISBN.getText().toString())){
            isbnUp = livro.getIsbn();
            lvre.setIsbn(isbnUp);

        }else {
            isbnUp = ISBN.getText().toString();
            lvre.setIsbn(isbnUp);
        }

        if (validar = validarEditText.CamposText(edtSinopse.getText().toString())){
            upSinopse = livro.getSinopse();
            lvre.setSinopse(upSinopse);
        }else {
            upSinopse = edtSinopse.getText().toString();
            lvre.setSinopse(upSinopse);
        }





        Boolean result = deDados.UpdateLivro(lvre, usuario.getIdLivro());


        if (result == true){

            Toast.makeText(Dados_Update_Livro.this, "Dados do Livro Atualizado", Toast.LENGTH_LONG).show();

            edtTitulo.setHint("Título: "+tituloUp);
            edtAutor.setHint("Autor: "+autorUp);
            edtAno.setHint("Ano: "+anoUp);
            edtEdicao.setHint("Edição: "+edicaoUp);
            edtPag.setHint("Páginas: "+pagUp);
            edtEditora.setHint("Editora: "+editoraUp);
            edtSinopse.setHint("Sinopse: "+upSinopse);
            ISBN.setHint("ISBN: "+isbnUp);

            edtTitulo.setText("");
            edtAutor.setText("");
            edtAno.setText("");
            edtPag.setText("");
            edtEdicao.setText("");
            edtEditora.setText("");
            ISBN.setText("");
            edtSinopse.setText("");


        }else Toast.makeText(Dados_Update_Livro.this, "Erro na atualização", Toast.LENGTH_LONG).show();


    }








}