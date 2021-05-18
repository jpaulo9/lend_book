package com.example.lendbook.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.lendbook.objetos.Emprestimo;
import com.example.lendbook.objetos.Livro;
import com.example.lendbook.objetos.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BaseDeDados extends SQLiteOpenHelper {

    private static final String NOME_DO_BANCO ="BOOK_DATA";
    private static final String NOME_TABELA = "TAB_LENDBOOK";
    private static final String TABELA_EMPREST = "EMPRESTIMO";
    private static final String TB_LIVROS = "LIVRO_X";
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[]imageInByte;

    public BaseDeDados(Context context){
        super(context,NOME_DO_BANCO, null, 8);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlQuery = "create table "+ NOME_TABELA+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT , nome varchar(20), email varchar(100), senha varchar(20) );";


        String sqlQlivro = "create table "+TB_LIVROS+"(id_livro INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo varchar(30), autor varchar(20), isbn varchar(15), paginas varchar(4), ano varchar(4)," +
                "editora varchar(30), edicao varchar (2), sinopse varchar(300), capa BLOB, idUser INTEGER)";

        String sqlEmprestimo =  " create table "+TABELA_EMPREST+" ( idEmprestimo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idUserDono INTEGER, idUserEmprestador INTEGER, idLivroEmprestado INTEGER, tempoEmprestimo INTEGER, status varchar(30)" +
                ",  nomeLivro varchar(50),  nomeUseremprestador varchar(50), nomeDono varchar(50))";

        db.execSQL(sqlQuery);
        db.execSQL(sqlQlivro);
        db.execSQL(sqlEmprestimo);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+NOME_TABELA);
        db.execSQL("DROP TABLE IF EXISTS "+TB_LIVROS);
        db.execSQL("DROP TABLE IF EXISTS "+TABELA_EMPREST);
        onCreate(db);

    }

    public long AddUserBanco(String nome, String email, String senha){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("email", email);
        values.put("senha", senha);


        return sqLiteDatabase.insert(NOME_TABELA, null, values);
    }

    public long AddLivroBanco(Bitmap imageLivro, String nome, String autor, String isbn, String paginas,
                                 String ano, String editora, String edicao, String sinopse, int idUser){


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Bitmap bitmapStore = imageLivro;
        byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapStore.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imageInByte = byteArrayOutputStream.toByteArray();

        ContentValues values = new ContentValues();

        values.put("titulo", nome);//10
        values.put("autor", autor);//2
        values.put("isbn", isbn);//7
        values.put("paginas", paginas);//8
        values.put("ano", ano);//1
        values.put("editora", editora);//5
        values.put("edicao", edicao);//4
        values.put("sinopse", sinopse);//9
        values.put("capa",imageInByte);//3
        values.put("idUser",idUser);//6


      return sqLiteDatabase.insert(TB_LIVROS, null, values);




    }

    public User LoginUser(String emails, String senhas){
        SQLiteDatabase  liteDatabase = this.getReadableDatabase();

                String sqlString ="select id, nome, email, senha from "+NOME_TABELA+" where email ='"+emails+
                "' and senha ='"+senhas+"'";
        Cursor cursor = liteDatabase.rawQuery(sqlString,null);


        User usuario = new User();
        int idUser = 0;
            if( cursor.moveToFirst()){

                String  nome, email, senha;
                idUser = cursor.getInt(cursor.getColumnIndex("id"));
                nome = cursor.getString(cursor.getColumnIndex("nome"));
                email = cursor.getString(cursor.getColumnIndex("email"));
                senha = cursor.getString(cursor.getColumnIndex("senha"));

                usuario.setId(idUser);
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha(senha);


            }else {

                usuario = null;
            }

        return usuario;
    }


    public List<Livro> MeuLivro(int idUser){

        SQLiteDatabase database = this.getReadableDatabase();
        String sqlString ="select titulo, autor, ano, capa, id_livro from "+TB_LIVROS+" where idUser ="+idUser+"";

        ArrayList<Livro> livrosUser = new ArrayList<>();

        Cursor cursor = database.rawQuery(sqlString,null);



        if (cursor.getCount()!= 0){

                  while (cursor.moveToNext()) {

                   Livro livro = new Livro() ;

                    String titulo, autor, ano;
                    titulo = cursor.getString(0);
                    autor = cursor.getString(1);
                    ano = cursor.getString(2);
                    int id = cursor.getInt(4);

                    byte[] imageBytes = cursor.getBlob(3);
                    Bitmap imgBtmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                      livro.setNomeLivro(titulo);
                      livro.setAutores(autor);
                      livro.setAno(ano);
                      livro.setCapa(imgBtmap);
                      livro.setIdLivro(id);



                    livrosUser.add(livro);

                }

        }


        return livrosUser;
    }

    public String UpNome(String dado, int id){

        String nome = "";
        SQLiteDatabase  liteDatabase = this.getReadableDatabase();


        ContentValues value = new ContentValues();

        value.put("nome", dado);

       long result = liteDatabase.update(NOME_TABELA,value, "id=?", new String[]{String.valueOf(id)});

       if (result == 1){

           nome = dado;

       }
        return nome;
    }


    public String Upemail(String dado, int id){

        String email = "";
        SQLiteDatabase  liteDatabase = this.getReadableDatabase();


        ContentValues value = new ContentValues();

        value.put("email", dado);

        long result = liteDatabase.update(NOME_TABELA,value, "id = ?", new String[]{String.valueOf(id)});

        if (result == 1){

            email = dado;

        }
        return email;
    }

    public String Upsenha(String dado, int id){

        String senha = "";
        SQLiteDatabase  liteDatabase = this.getReadableDatabase();


        ContentValues value = new ContentValues();

        value.put("senha", dado);

        long result = liteDatabase.update(NOME_TABELA,value, "id = ?", new String[]{String.valueOf(id)});

        if (result == 1){

            senha = dado;

        }
        return senha;
    }

    public Boolean DeleteLivro( int id){

        SQLiteDatabase  liteDatabase = this.getReadableDatabase();



      //  long res = liteDatabase.delete(TB_LIVROS, where,null);

        long res = liteDatabase.delete(TB_LIVROS, "id_livro = ?", new String[]{String.valueOf(id)});

        if (res == -1){

            return false;

        }else {
            return true;
        }




    }

    public Livro SelecionarLivro(int idLivro){

        SQLiteDatabase database = this.getReadableDatabase();
        String sqlString ="select titulo, autor, ano, capa, id_livro, editora, edicao," +
                "paginas, sinopse, isbn, idUser from "+TB_LIVROS+" where id_livro ="+idLivro+"";


        Cursor cursor = database.rawQuery(sqlString,null);

        Livro livro = new Livro() ;

        if (cursor.moveToFirst()){


                String titulo, autor, ano, isbn, paginas, edicao, editora, sinopse;
                titulo = cursor.getString(0);
                autor = cursor.getString(1);
                ano = cursor.getString(2);
                isbn = cursor.getString(9);
                paginas = cursor.getString(7);
                edicao = cursor.getString(6);
                editora = cursor.getString(5);
                sinopse = cursor.getString(8);
                int id = cursor.getInt(4);
                int idUser = cursor.getInt(10);

                byte[] imageBytes = cursor.getBlob(3);
                Bitmap imgBtmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                livro.setNomeLivro(titulo);
                livro.setAutores(autor);
                livro.setAno(ano);
                livro.setCapa(imgBtmap);
                livro.setIdLivro(id);
                livro.setEdicao(edicao);
                livro.setEditora(editora);
                livro.setIsbn(isbn);
                livro.setPaginas(paginas);
                livro.setSinopse(sinopse);
                livro.setIdUser(idUser);




            }




        return livro ;
    }


    public Boolean upCapa( Bitmap bitmap, int idLivro){


        SQLiteDatabase  liteDatabase = this.getReadableDatabase();
        Bitmap bitmapStore = bitmap;


        byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapStore.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        imageInByte = byteArrayOutputStream.toByteArray();

       ContentValues value = new ContentValues();

       value.put("capa", imageInByte);




        long result = liteDatabase.update(TB_LIVROS,value, "id_livro = ?", new String[]{String.valueOf(idLivro)});

        if (result == 1){

           return  true;

        }
        else {
            return false;
        }

    }

    public Boolean UpdateLivro(Livro dado, int idLivro){


        SQLiteDatabase  liteDatabase = this.getReadableDatabase();

        Boolean update =  null;
        Livro livro = dado;
        ContentValues values = new ContentValues();



        values.put("titulo", livro.getNomeLivro());
        values.put("ano", livro.getAno());
        values.put("autor", livro.getAutores());
        values.put("edicao", livro.getEdicao());
        values.put("paginas", livro.getPaginas());
        values.put("editora", livro.getEditora());
        values.put("isbn", livro.getIsbn());
        values.put("sinopse", livro.getSinopse());




        long result = liteDatabase.update(TB_LIVROS,values, "id_livro = ?", new String[]{String.valueOf(idLivro)});

        if (result == 1){

            update = true;

        }else{
            update = false;
        }


        return update;
    }


    public List<Livro> LivrosColecao(){

        SQLiteDatabase database = this.getReadableDatabase();
        String sqlString ="select titulo, autor, ano, capa, id_livro, idUser from "+TB_LIVROS;

        ArrayList<Livro> livrosUser = new ArrayList<>();

        Cursor cursor = database.rawQuery(sqlString,null);



        if (cursor.getCount()!= 0){

            while (cursor.moveToNext()) {

                Livro livro = new Livro() ;

                String titulo, autor, ano;
                titulo = cursor.getString(0);
                autor = cursor.getString(1);
                ano = cursor.getString(2);
                int id = cursor.getInt(4);
                int idUser = cursor.getInt(5);

                byte[] imageBytes = cursor.getBlob(3);
                Bitmap imgBtmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                livro.setNomeLivro(titulo);
                livro.setAutores(autor);
                livro.setAno(ano);
                livro.setCapa(imgBtmap);
                livro.setIdLivro(id);
                livro.setIdUser(idUser);



                livrosUser.add(livro);

            }

        }


        return livrosUser;
    }


    public long AddEmprestimo(Emprestimo emprestimo){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//
        if (emprestimo.getIdUserDono()!= emprestimo.getIdUserEmprestador()) {
            ContentValues values = new ContentValues();
            if (emprestimo != null) {
                values.put("idUserDono", emprestimo.getIdUserDono());
                values.put("idUserEmprestador", emprestimo.getIdUserEmprestador());
                values.put("idLivroEmprestado", emprestimo.getIdLivroEmprestado());
                values.put("tempoEmprestimo", emprestimo.getTempoEmprestimo());
                values.put("status", emprestimo.getStatus());
                values.put("nomeLivro", emprestimo.getNomeLivro());
                values.put("nomeUseremprestador", emprestimo.getNomeUseremprestador());
                values.put("nomeDono", emprestimo.getNomeDno());
                return sqLiteDatabase.insert(TABELA_EMPREST, null, values);

            } else return -1;

        }else return -1;




    }

    public User getUser(int id){
        SQLiteDatabase  liteDatabase = this.getReadableDatabase();

        String sqlString ="select id, nome, email, senha from "+NOME_TABELA+" where id = "+id;
        Cursor cursor = liteDatabase.rawQuery(sqlString,null);


        User usuario = new User();
        int idUser = 0;
        if( cursor.moveToFirst()){

            String  nome, email, senha;
            idUser = cursor.getInt(cursor.getColumnIndex("id"));
            nome = cursor.getString(cursor.getColumnIndex("nome"));
            email = cursor.getString(cursor.getColumnIndex("email"));
            senha = cursor.getString(cursor.getColumnIndex("senha"));

            usuario.setId(idUser);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);


        }else {

            usuario = null;
        }

        return usuario;
    }

    public List<Emprestimo> Solicitacoes(int idUser){

        SQLiteDatabase database = this.getReadableDatabase();

        String sqlString ="select idEmprestimo, idUserDono, idUserEmprestador, nomeUseremprestador, status, nomeLivro " +
                " from "+TABELA_EMPREST+" where idUserDono = "+idUser+"";

        List<Emprestimo> emprestimos = new ArrayList<>();

        Cursor cursor = database.rawQuery(sqlString,null);



        if (cursor.getCount()!= 0){

            while (cursor.moveToNext()) {

              Emprestimo empt = new Emprestimo();

                int idMp, idUdono, idUsSolic;
                String nomeUserSolicitante, statusSolicitacao, nomeLivro;

                nomeLivro = cursor.getString(5);
                nomeUserSolicitante = cursor.getString(3);
                statusSolicitacao = cursor.getString(4);
                idMp = cursor.getInt(0);
               idUdono = cursor.getInt(1);
               idUsSolic = cursor.getInt(2);

               empt.setIdEmprestimo(idMp);
                empt.setIdUserDono(idUdono);
                empt.setIdUserEmprestador(idUsSolic);
                empt.setNomeLivro(nomeLivro);
                empt.setNomeUseremprestador(nomeUserSolicitante);
                empt.setStatus(statusSolicitacao);

                emprestimos.add(empt);

            }

        }


        return emprestimos;
    }

    public List<Emprestimo> MeusPedidos(int idUser){

        SQLiteDatabase database = this.getReadableDatabase();

        String sqlString ="select idEmprestimo, idUserDono, idUserEmprestador, nomeDono, status, nomeLivro " +
                " from "+TABELA_EMPREST+" where idUserEmprestador ="+idUser+" ";

        List<Emprestimo> emprestimos = new ArrayList<>();

        Cursor cursor = database.rawQuery(sqlString,null);



        if (cursor.getCount()!= 0){

            while (cursor.moveToNext()) {

                Emprestimo empt = new Emprestimo() ;

                int idMp, idUdono, idUsSolic;
                String nomeUserSolicitante, statusSolicitacao, nomeLivro;
                nomeLivro = cursor.getString(5);
                nomeUserSolicitante = cursor.getString(3);
                statusSolicitacao = cursor.getString(4);
                idMp = cursor.getInt(0);
                idUdono = cursor.getInt(1);
                idUsSolic = cursor.getInt(2);

                empt.setIdEmprestimo(idMp);
                empt.setIdUserDono(idUdono);
                empt.setIdUserEmprestador(idUsSolic);
                empt.setNomeLivro(nomeLivro);
                empt.setNomeDno(nomeUserSolicitante);
                empt.setStatus(statusSolicitacao);

                emprestimos.add(empt);

            }

        }


        return emprestimos;
    }



    public long UpStatus(String dado, int idEmprestimo){

        String senha = "";
        SQLiteDatabase  liteDatabase = this.getReadableDatabase();


        ContentValues value = new ContentValues();

        value.put("status", dado);

        long result = liteDatabase.update(TABELA_EMPREST,value, "idEmprestimo = ?", new String[]{String.valueOf(idEmprestimo)});

        if (result == 1){

            return result;

        }else return -1;

    }




}
