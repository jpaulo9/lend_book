package com.example.lendbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lendbook.banco.BaseDeDados;
import com.example.lendbook.objetos.Livro;
import com.example.lendbook.objetos.User;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Colecao extends Fragment {

    RecyclerView listView;
    List<Livro> listLivros;
    User usuario;
    BaseDeDados baseDeDados;
    View view;
    TextView tAviso;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_colecao, container, false);

        listView = (RecyclerView)view.findViewById(R.id.lista_colecao);
        listLivros = new ArrayList<>();
        tAviso = (TextView) view.findViewById(R.id.tAviso);

       baseDeDados = new BaseDeDados(getActivity());
        Bundle dados = getActivity().getIntent().getExtras();

        if ((dados!= null) && (dados.containsKey("User"))) {
            usuario = (User) dados.getSerializable("User");

        }else {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }


        Colecao();

        return view;
    }

    public void Colecao(){

        listLivros.clear();

        listLivros = baseDeDados.LivrosColecao();

        if (!listLivros.isEmpty()){

            ListarLivrosColecao livrosColecao = new ListarLivrosColecao(getActivity(), listLivros, usuario);
            listView.setLayoutManager(new LinearLayoutManager(getActivity()));
            listView.setHasFixedSize(true);
            listView.setAdapter(livrosColecao);



        }else {

            tAviso.setText("Coleção Vazia");
        }


    }
}
