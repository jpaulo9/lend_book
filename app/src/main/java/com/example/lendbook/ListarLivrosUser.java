package com.example.lendbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lendbook.banco.BaseDeDados;
import com.example.lendbook.objetos.Livro;
import com.example.lendbook.objetos.User;


import java.util.List;

public class ListarLivrosUser extends RecyclerView.Adapter<ListarLivrosUser.MyViewHolder> {

    Context context;
    private static List<Livro> livros;
    private static User mid;

    public ListarLivrosUser(Context context, List<Livro> livrosU, User users){

        this.context = context;
        this.livros = livrosU;

        this.mid = users;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;


            view = LayoutInflater.from(context).inflate(R.layout.dados_livro_user, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view, parent.getContext());

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.titulo.setText(livros.get(position).getNomeLivro());

        holder.autor.setText(livros.get(position).getAutores());
        holder.ano.setText(livros.get(position).getAno());


        holder.imageLivro.setImageBitmap(livros.get(position).getCapa());
        holder.btRemove.setImageResource(R.drawable.ic_baseline_close_24);
        holder.btn_detalhes.setText("DETALHES");


            }


    @Override
    public int getItemCount() {
        return livros.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageLivro, btRemove;
        private TextView titulo, ano, autor;
        private Button btn_detalhes;




      public MyViewHolder (@NonNull final View itemView, final Context context){
          super(itemView);

                imageLivro = (ImageView) itemView.findViewById(R.id.setImageLivro);

                titulo = (TextView) itemView.findViewById(R.id.txtTitle);
                autor = (TextView) itemView.findViewById(R.id.txtAutor);
                ano = (TextView) itemView.findViewById(R.id.txtAno);
                btRemove = (ImageView) itemView.findViewById(R.id.btnRemove);
                btn_detalhes = (Button) itemView.findViewById(R.id.btn_detalhes) ;



                btRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Livro livro = livros.get(getLayoutPosition());

                      mid.setIdLivro(livro.getIdLivro());


                        BaseDeDados baseDeDados = new BaseDeDados(context);
                        Boolean
                                result = baseDeDados.DeleteLivro(livro.getIdLivro());

                        if (result == true){

                            Toast.makeText(context,"Livro removido\n Recarregue a página!", Toast.LENGTH_SHORT).show();
                            btn_detalhes.setEnabled(false);


                        }






                    }
                });


                btn_detalhes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Livro livro = livros.get(getLayoutPosition());

                        mid.setIdLivro(livro.getIdLivro());

                        Intent intent = new Intent(context, Dados_Update_Livro.class);
                        intent.putExtra("User", mid);
                        ((AppCompatActivity) context).startActivityForResult(intent, 0);
                        ((AppCompatActivity) context).finish();


                    }
                });

      }
    }



}