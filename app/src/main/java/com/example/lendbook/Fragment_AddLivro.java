package com.example.lendbook;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.lendbook.banco.BaseDeDados;
import com.example.lendbook.objetos.ImageLivro;
import com.example.lendbook.objetos.User;
import com.squareup.picasso.Picasso;

import java.util.Random;

import static android.app.Activity.RESULT_OK;

public class Fragment_AddLivro extends Fragment {
    Uri file;
    View view;
    ImageView imageView;
    Button btnSalvrLvr;
    EditText lvrNome, lvrAutor, lvrPag, lvrAno, lvrEdtora, lvrEdicao,lvrIsbn, lvrSinopse;

    BaseDeDados deDados;
    Bitmap imagem;
    User usuario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adicionar_livro, container, false);

        imageView = (ImageView) view.findViewById(R.id.img_selectCap);
        btnSalvrLvr = (Button) view.findViewById(R.id.btn_addLivro);
        deDados = new BaseDeDados(getActivity());

        lvrNome = (EditText) view.findViewById(R.id.edt_lvrNome);
        lvrAutor = (EditText) view.findViewById(R.id.edt_lvrAutor);
        lvrAno = (EditText) view.findViewById(R.id.edt_lvrAno);
        lvrEdicao = (EditText) view.findViewById(R.id.edt_lvrEdc);
        lvrEdtora = (EditText) view.findViewById(R.id.edt_lvrEdtt);
        lvrIsbn = (EditText) view.findViewById(R.id.edt_lvrISBN);
        lvrSinopse = (EditText) view.findViewById(R.id.edt_lvrSinop);
        lvrPag = (EditText) view.findViewById(R.id.edt_lvrPag);

        Bundle daddos = getActivity().getIntent().getExtras();
        if ((daddos!= null) && (daddos.containsKey("User"))) {
            usuario = (User) daddos.getSerializable("User");

        }else {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 0);


        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TirarFoto();
            }
        });

        btnSalvrLvr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddLivros();

            }
        });


        return view;

    }

    public void TirarFoto(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK ){

            Bundle extras = data.getExtras();
             imagem = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imagem);
//

        }

        super.onActivityResult(requestCode, resultCode , data);
    }


    public void AddLivros(){

        String titulo, autors, anos, edicao, editora, paginas, isbn, sinopse;

        titulo = lvrNome.getText().toString();
        autors = lvrAutor.getText().toString();
        anos = lvrAno.getText().toString();
        edicao = lvrEdicao.getText().toString();
        editora = lvrEdtora.getText().toString();
        paginas = lvrPag.getText().toString();
        isbn = lvrIsbn.getText().toString();
        sinopse = lvrSinopse.getText().toString();

        ValidarEditText validarEditText = new ValidarEditText();

        Boolean validar = false;

        if (validar = validarEditText.CamposText(titulo)){
            lvrNome.setError("Campo obrigatório");
        }else if (validar = validarEditText.CamposText(autors)){
            lvrAutor.setError("Campo obrigatório");
        }else if (validar = validarEditText.CamposText(isbn)){
            lvrIsbn.setError("Campo obrigatório");
        }else if (validar = validarEditText.CamposText(paginas)){
            lvrPag.setError("Campo obrigatório");
        }else if (validar = validarEditText.CamposText(anos)){
            lvrAno.setError("Campo obrigatório");
        }else if (validar = validarEditText.CamposText(editora)){
            lvrEdtora.setError("Campo obrigatório");
        }else if (validar = validarEditText.CamposText(edicao)){
            lvrEdicao.setError("Campo obrigatório");
        }else if (validar = validarEditText.CamposText(sinopse)){
            lvrSinopse.setError("Campo obrigatório");
        }

        if (!validar){


            if (imagem != null && imageView.getDrawable() != null){

                notificatcao_appLend notificar = new
                        notificatcao_appLend(getActivity());

                long id = deDados.AddLivroBanco(imagem,titulo,autors, isbn,paginas,anos, editora,edicao,sinopse, usuario.getId());
                Toast.makeText(getActivity(), "Livro Adicionado ", Toast.LENGTH_LONG).show();
//
                NotificationCompat.Builder builder =
                        notificar.builder
                ("Um novo livro foi adicionado por "+usuario.getNome()
                                ," Título: \n"+titulo+" Autor(es): "+autors);
                notificar.getManager().notify(new Random().nextInt(), builder.build());


                lvrSinopse.setText("");
                lvrEdicao.setText("");
                lvrEdtora.setText("");
                lvrPag.setText("");
                lvrAno.setText("");
                lvrIsbn.setText("");
                lvrAutor.setText("");
                lvrNome.setText("");
                imageView.setImageResource(R.drawable.ic_baseline_add_photo_alternate_24);

            }else {
                Toast.makeText(getActivity(),"Por favor coloque uma imagem da capa do livro", Toast.LENGTH_LONG).show();

            }


        }




    }






}
