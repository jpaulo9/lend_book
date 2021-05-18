package com.example.lendbook.objetos;

public class Emprestimo {

    int idEmprestimo, idUserDono, idUserEmprestador, idLivroEmprestado, tempoEmprestimo;
    String status, nomeLivro, nomeUseremprestador, nomeDno;

    public Emprestimo (){

    }


    public Emprestimo(int idEmprestimo, int idUserDono, int idUserEmprestador,
                      int idLivroEmprestado, int tempoEmprestimo, String status, String nomeLivro, String nomeUseremprestador, String nomeDono) {
        this.idUserDono = idUserDono;
        this.idUserEmprestador = idUserEmprestador;
        this.idLivroEmprestado = idLivroEmprestado;
        this.tempoEmprestimo = tempoEmprestimo;
        this.status = status;
        this.idEmprestimo = idEmprestimo;
        this.nomeLivro = nomeLivro;
        this.nomeUseremprestador = nomeUseremprestador;
        this.nomeDno = nomeDono;
    }

    public String getNomeDno() {
        return nomeDno;
    }

    public void setNomeDno(String nomeDno) {
        this.nomeDno = nomeDno;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getNomeUseremprestador() {
        return nomeUseremprestador;
    }

    public void setNomeUseremprestador(String nomeUseremprestador) {
        this.nomeUseremprestador = nomeUseremprestador;
    }

    public int getIdUserDono() {
        return idUserDono;
    }

    public void setIdUserDono(int idUserDono) {
        this.idUserDono = idUserDono;
    }

    public int getIdUserEmprestador() {
        return idUserEmprestador;
    }

    public void setIdUserEmprestador(int idUserEmprestador) {
        this.idUserEmprestador = idUserEmprestador;
    }

    public int getIdLivroEmprestado() {
        return idLivroEmprestado;
    }

    public void setIdLivroEmprestado(int idLivroEmprestado) {
        this.idLivroEmprestado = idLivroEmprestado;
    }

    public int getTempoEmprestimo() {
        return tempoEmprestimo;
    }

    public void setTempoEmprestimo(int tempoEmprestimo) {
        this.tempoEmprestimo = tempoEmprestimo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
