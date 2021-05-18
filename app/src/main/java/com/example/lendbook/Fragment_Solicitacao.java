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
import com.example.lendbook.objetos.Emprestimo;
import com.example.lendbook.objetos.Livro;
import com.example.lendbook.objetos.User;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Solicitacao extends Fragment {

    RecyclerView listView, listview2;
    List<Emprestimo> listSoliticao;
    List<Emprestimo> listPedidos;
    View view;
    User usuario;
    BaseDeDados baseDeDados;
    TextView textView, tx2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_emprestimos, container, false);

        listView = (RecyclerView) view.findViewById(R.id.list_solicitacoes);
        listview2 = (RecyclerView) view.findViewById(R.id.list_mSolicitacoes);

        textView = (TextView) view.findViewById(R.id.tSolictinul);
        tx2 = (TextView) view.findViewById(R.id.tSolicPedidos);

        listSoliticao = new ArrayList<>();
        listPedidos = new ArrayList<>();

        baseDeDados = new BaseDeDados(getActivity());

        Bundle dados = getActivity().getIntent().getExtras();

        if ((dados!= null) && (dados.containsKey("User"))) {
            usuario = (User) dados.getSerializable("User");

        }else {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }


        MostrarSolicitacoes();
        MeusPedidos();
        return view;
    }


    public void MostrarSolicitacoes (){

        listSoliticao.clear();
        listSoliticao = baseDeDados.Solicitacoes(usuario.getId());



        if (!listSoliticao.isEmpty()){

            int cont = listSoliticao.size();

            textView.setText("Solicitações de empréstimos "+cont);
            ListarLivrosEmprestados livrosUser = new ListarLivrosEmprestados(getActivity(), listSoliticao, usuario);
            listView.setLayoutManager(new LinearLayoutManager(getActivity()));
            listView.setHasFixedSize(true);
            listView.setAdapter(livrosUser);

        }else {

            listView.setAdapter(null);
            textView.setText("Sem solicitações 0");
        }

    }


    public void MeusPedidos()
    {
        listPedidos.clear();
        listPedidos = baseDeDados.MeusPedidos(usuario.getId());



        if (!listPedidos.isEmpty()){
                int cont = listPedidos.size();

            tx2.setText("Meus Pedidos "+cont);
            ListarLivrosSolicitados livrosUser = new ListarLivrosSolicitados(getActivity(), listPedidos, usuario);
            listview2.setLayoutManager(new LinearLayoutManager(getActivity()));
            listview2.setHasFixedSize(true);
            listview2.setAdapter(livrosUser);

        }else {

            listview2.setAdapter(null);
            tx2.setText("Seus Pedidos 0");
        }



    }



}
