package com.example.lendbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.lendbook.banco.BaseDeDados;
import com.example.lendbook.objetos.User;

public class Fragment_Mdados extends Fragment {

    View view;

    EditText edtNome, edtEmail, edtSenha;
    TextView txtNome, txtEmail, txtSenha;
    ImageView btnNome, btnEmail, btnSenha;
    User usuario;
    BaseDeDados deDados ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_meus_dados, container, false);

        deDados = new BaseDeDados(getActivity());
        edtNome = (EditText) view.findViewById(R.id.edtUpnome);
        edtEmail = (EditText) view.findViewById(R.id.edtUpemail);
        edtSenha = (EditText) view.findViewById(R.id.edtUpsenha);

        txtNome = (TextView) view.findViewById(R.id.txtNomeUser);
        txtEmail = (TextView) view.findViewById(R.id.txtEmailUser);
        txtSenha = (TextView) view.findViewById(R.id.txtSenhaUser);

        btnNome = (ImageView) view.findViewById(R.id.imgBtnome);
        btnEmail = (ImageView) view.findViewById(R.id.imgBtemail);
        btnSenha = (ImageView) view.findViewById(R.id.imgBtsenha);

        Bundle daddos = getActivity().getIntent().getExtras();
        if ((daddos!= null) && (daddos.containsKey("User"))) {
            usuario = (User) daddos.getSerializable("User");

            txtNome.setText("NOME ATUAL: "+usuario.getNome());
            txtEmail.setText("E-MAIL ATUAL: "+usuario.getEmail());
            txtSenha.setText("SENHA ATUAL: "+usuario.getSenha());

        }else {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

               btnNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarEditText validarEditText = new ValidarEditText();
                Boolean validar = false;
                String newnome = edtNome.getText().toString();

                if (validar = validarEditText.CamposText(newnome)){

                    edtNome.setError("Insira o novo nome!");
                }

                if(!validar){

                    newnome = deDados.UpNome(newnome, usuario.getId());
                    txtNome.setText("NOME ATUAL: "+newnome);
                    edtNome.setText("");

                }

            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarEditText validarEditText = new ValidarEditText();
                Boolean validar = false;
                String newemail = edtEmail.getText().toString();

                if (validar = validarEditText.Email(newemail)){

                    edtEmail.setError("Insira um email corretamente!");
                }

                if(!validar){

                    newemail = deDados.Upemail(newemail, usuario.getId());
                    txtEmail.setText("E-MAIL ATUAL: "+newemail);
                    edtEmail.setText("");

                }

            }
        });

        btnSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarEditText validarEditText = new ValidarEditText();
                Boolean validar = false;
                String newsenha = edtSenha.getText().toString();

                if (validar = validarEditText.CamposText(newsenha)){

                    edtSenha.setError("Insira a nova senha!");
                }

                if(!validar){

                    newsenha = deDados.Upsenha(newsenha, usuario.getId());
                    txtSenha.setText("SENHA ATUAL: "+newsenha);
                    edtSenha.setText("");

                }

            }
        });

        return view;
    }







}
