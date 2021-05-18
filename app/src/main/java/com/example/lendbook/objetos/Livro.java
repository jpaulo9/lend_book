package com.example.lendbook.objetos;

import android.graphics.Bitmap;

public class Livro  {

    int idLivro;
    String isbn, edicao, paginas, ano;
    String nomeLivro, autores, editora, sinopse;
    int idUser;
    Bitmap capa;

    public Livro(){

    }

    public Livro(Bitmap capa, int idLivro, String isbn, String edicao, String paginas,
                 String ano, String nomeLivro, String autores, String editora, String sinopse, int idUser) {
        this.idLivro = idLivro;
        this.isbn = isbn;
        this.edicao = edicao;
        this.paginas = paginas;
        this.ano = ano;
        this.nomeLivro = nomeLivro;
        this.autores = autores;
        this.editora = editora;
        this.sinopse = sinopse;
        this.capa = capa;
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Bitmap getCapa() {
        return capa;
    }

    public void setCapa(Bitmap capa) {
        this.capa = capa;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }


}
