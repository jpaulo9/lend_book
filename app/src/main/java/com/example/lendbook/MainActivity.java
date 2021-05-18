package com.example.lendbook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;


import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lendbook.banco.BaseDeDados;
import com.example.lendbook.objetos.User;


public class MainActivity extends AppCompatActivity{

    TextView btnCadastro;
    Button btnLogin;
    BaseDeDados  deDados;
    EditText lgemail, lgsenha;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deDados = new BaseDeDados(MainActivity.this);
        lgemail = (EditText) findViewById(R.id.edtLogin_email);
        lgsenha = (EditText) findViewById(R.id.edtLogin_senha);
        btnCadastro = (TextView) findViewById(R.id.txtCadastro);
        btnLogin = (Button) findViewById(R.id.btnlogin);






        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroUser.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                login();

            }
        });




    }





    public void login(){
        String email, senha;
        email = lgemail.getText().toString();
        senha = lgsenha.getText().toString();

        Boolean validar = false;

        ValidarEditText validarEditText = new ValidarEditText();

        if (validar= validarEditText.Email(email)){
            lgemail.setError("Email inválido");
        }else if (validar= validarEditText.CamposText(senha)){
            lgsenha.setError("Senha inválida");
        }
        if (!validar){

            User usuario = deDados.LoginUser(email, senha);

            if (usuario!= null){


                Intent intent = new Intent(MainActivity.this, HomeApp.class);
                intent.putExtra("User", usuario);


                startActivity(intent);
                finish();
            }else {

              AlertDialog.Builder builder = new AlertDialog.Builder(this);
              builder.setTitle("USUÁRIO NÃO ENCONTRADO").setMessage("Verifique se a sua senha está correta ou se o" +
                      "seu email está igual ao que você cadastrou.\nCaso isso não funcione acesse a opção criar conta.")
                      .setPositiveButton("Ok", null).show();
              lgemail.setText("");
              lgsenha.setText("");

            }

        }


    }



}