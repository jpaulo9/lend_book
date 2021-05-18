package com.example.lendbook;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lendbook.banco.BaseDeDados;
import com.example.lendbook.objetos.Emprestimo;
import com.example.lendbook.objetos.Livro;
import com.example.lendbook.objetos.User;

import java.util.List;

public class ListarLivrosColecao extends RecyclerView.Adapter<ListarLivrosColecao.MyViewHolder> {

    Context context;
    private static List<Livro> livros;
    private static User mid;

    public ListarLivrosColecao(Context context, List<Livro> livrosU, User users){

        this.context = context;
        this.livros = livrosU;

        this.mid = users;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;


            view = LayoutInflater.from(context).inflate(R.layout.fragment_dados_colecao, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view, parent.getContext());

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.titulo.setText(livros.get(position).getNomeLivro());

        holder.autor.setText(livros.get(position).getAutores());



        holder.imageLivro.setImageBitmap(livros.get(position).getCapa());
        holder.btn_emprest.setText("EMPRESTAR");


            }


    @Override
    public int getItemCount() {
        return livros.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageLivro;
        private TextView titulo, autor;
        private Button btn_emprest;




      public MyViewHolder (@NonNull final View itemView, final Context context){
          super(itemView);

                imageLivro = (ImageView) itemView.findViewById(R.id.imgColecao);

                titulo = (TextView) itemView.findViewById(R.id.nomeColecao);
                autor = (TextView) itemView.findViewById(R.id.autorColecao);
                btn_emprest = (Button) itemView.findViewById(R.id.btn_emprestar);




                btn_emprest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Livro livro = livros.get(getLayoutPosition());

                      mid.setIdLivro(livro.getIdLivro());


                        BaseDeDados baseDeDados = new BaseDeDados(context);

                        Emprestimo emprestimo = new Emprestimo();
                        Toast.makeText(context, "Id "+livro.getIdUser(), Toast.LENGTH_SHORT).show();


                        User use = baseDeDados.getUser(livro.getIdUser());

                        if (use!= null){

                            emprestimo.setIdLivroEmprestado(livro.getIdLivro());
                            emprestimo.setIdUserDono(livro.getIdUser());
                            emprestimo.setIdUserEmprestador(mid.getId());
                            emprestimo.setTempoEmprestimo(7);
                            emprestimo.setStatus("Aguardando Resposta");
                            emprestimo.setNomeLivro(livro.getNomeLivro());
                            emprestimo.setNomeUseremprestador(mid.getNome());
                            emprestimo.setNomeDno(use.getNome());

                            if (emprestimo!= null){


                                long result = baseDeDados.AddEmprestimo(emprestimo);

                                if (result != -1) {
                                    AlertDialog.Builder builder
                                            = new AlertDialog.Builder(context);
                                    builder.setTitle("Empréstimo de Livro").
                                            setMessage("Seu pedido foi enviado aguarde a resposta do proprietário!")
                                            .setPositiveButton("Ok", null).show();

                                }else Toast.makeText(context,"Você já possui este livro!", Toast.LENGTH_LONG).show();


                        }else Toast.makeText(context,"Error ", Toast.LENGTH_LONG).show();

                        }
//








                    }
                });




      }
    }



}