package com.example.lendbook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lendbook.banco.BaseDeDados;
import com.example.lendbook.objetos.Livro;
import com.example.lendbook.objetos.User;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Mlivros extends Fragment {

    RecyclerView listView;
    List<Livro> listLivros;
    View view;
    User usuario;
    BaseDeDados baseDeDados;
    TextView textView;
    private static final int REQUEST_CODE = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_meus_livros, container, false);

        listView = (RecyclerView) view.findViewById(R.id.list_livrosUser);
        textView = (TextView) view.findViewById(R.id.semLivros);
        listLivros = new ArrayList<>();
        baseDeDados = new BaseDeDados(getActivity());


        Bundle dados = getActivity().getIntent().getExtras();

        if ((dados!= null) && (dados.containsKey("User"))) {
            usuario = (User) dados.getSerializable("User");


        }else {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }



        MostrarMeusLivros();
        return view;
    }


    public void MostrarMeusLivros (){

        listLivros.clear();
        listLivros = baseDeDados.MeuLivro(usuario.getId());



        if (!listLivros.isEmpty()){


            ListarLivrosUser livrosUser = new ListarLivrosUser(getActivity(), listLivros, usuario);
            listView.setLayoutManager(new LinearLayoutManager(getActivity()));
            listView.setHasFixedSize(true);
            listView.setAdapter(livrosUser);

        }else {

            textView.setText("Você ainda não adicionou nenhum livro");
        }

    }




}
