package com.example.lendbook.objetos;

import java.io.Serializable;

public class User  implements Serializable {
    String  nome, email, senha;
int id, idLivro;

    public User(){}

    public User(int id, String nome, String email, String senha, int idLivros) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idLivro = idLivros;

    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
