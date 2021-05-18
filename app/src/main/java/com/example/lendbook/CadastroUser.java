package com.example.lendbook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lendbook.banco.BaseDeDados;

public class CadastroUser extends AppCompatActivity {


    TextView btnVolt;


    EditText cadNome, cadEmail, cadSenha;
    Button btnAddUser;
//    double lat = 0, lng = 0;

    private static final int REQUEST_CODE = 1;
        BaseDeDados acessoBanco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);

        cadEmail = (EditText) findViewById(R.id.edt_cadEmail);
        cadNome = (EditText) findViewById(R.id.edt_cadNome);
        cadSenha = (EditText) findViewById(R.id.edt_cadSenha);
        btnAddUser = (Button) findViewById(R.id.btnEnviar);

        acessoBanco = new BaseDeDados(CadastroUser.this);

        btnVolt = (TextView) findViewById(R.id.btnVoltar);



        btnVolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroUser.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUser();
            }
        });


    }






    public void AddUser(){

        String nome, email, senha;

        nome = cadNome.getText().toString();
        email = cadEmail.getText().toString();
        senha = cadSenha.getText().toString();

        Boolean validar = false;

        ValidarEditText validarEditText = new ValidarEditText();
        if (validar = validarEditText.CamposText(nome)){

            cadNome.setError("Campo Vazio");

        }else if(validar = validarEditText.Email(email)){
            cadEmail.setError("E-mail inválido");

        }else if(validar = validarEditText.CamposText(senha)){
            cadSenha.setError("Campo Vazio");
        }
        if (!validar){

           long id = acessoBanco.AddUserBanco(nome, email, senha);

            cadNome.setText("");
            cadSenha.setText("");
            cadEmail.setText("");



                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("CADASTRO").setMessage("Seu cadstro foi concluído com sucesso!").
                        setPositiveButton("Ok", null).show();


        }


    }


}