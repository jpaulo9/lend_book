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

public class ListarLivrosEmprestados extends RecyclerView.Adapter<ListarLivrosEmprestados.MyViewHolder> {

    Context context;
    private static List<Emprestimo> livros;

    private static User mid;

    public ListarLivrosEmprestados(Context context, List<Emprestimo> livrosU, User users){

        this.context = context;
        this.livros = livrosU;

        this.mid = users;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;


            view = LayoutInflater.from(context).inflate(R.layout.dados_emprestimo, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view, parent.getContext());

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


//        holder.titulo.setText(livros.get(position).getNomeLivro());
//

        holder.nomeLivro.setText("Título: "+livros.get(position).getNomeLivro());
        holder.nomeParaEmprest.setText(livros.get(position).getNomeUseremprestador()+ " Solicitou empréstimo do seu livro");
        holder.status_1.setText("Status: "+ livros.get(position).getStatus());
        holder.imageLivro.setImageResource(R.drawable.ic_launcher_1_foreground);
        holder.btn_aceitar.setText("DISPONÍVEL");
        holder.btn_recusar.setText("INDISPONÍVEL");


            }


    @Override
    public int getItemCount() {
        return livros.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageLivro, imgPedido;
        private TextView nomeLivro, nomeDono, nomeParaEmprest, status_1, status_2, nomeLivroPedido;
        private Button btn_aceitar, btn_recusar;




      public MyViewHolder (@NonNull final View itemView, final Context context){
          super(itemView);

          imageLivro = (ImageView) itemView.findViewById(R.id.imgSolicite);
          imgPedido = (ImageView) itemView.findViewById(R.id.imgPedido);



          nomeLivro = (TextView) itemView.findViewById(R.id.txtNomeLivroemp);
          nomeParaEmprest = (TextView) itemView.findViewById(R.id.txtNomeUserEmp);
          status_1 = (TextView) itemView.findViewById(R.id.txtStatus);

          nomeDono = (TextView) itemView.findViewById(R.id.txtPusernome);
          nomeLivroPedido = (TextView) itemView.findViewById(R.id.txtPusernome);
          status_2 = (TextView) itemView.findViewById(R.id.statusPedido);

          btn_aceitar = (Button) itemView.findViewById(R.id.btn_aceitar);
          btn_recusar = (Button) itemView.findViewById(R.id.btn_recusar);


                btn_aceitar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Emprestimo livro = livros.get(getLayoutPosition());

                      //mid.setIdLivro(livro.getIdLivro());


                        BaseDeDados baseDeDados = new BaseDeDados(context);
                        long
                                result = baseDeDados.UpStatus("DISPONÍVEL", livro.getIdEmprestimo());

                        if (result != -1) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("LIVRO EMPRESTADO").setMessage("O interessado irá se deslocar até o seu local especificado na sua localização!")
                                    .setPositiveButton("Ok", null).show();
                            btn_aceitar.setEnabled(false);
                            btn_recusar.setEnabled(false);
                        }else Toast.makeText(context, "Erro", Toast.LENGTH_LONG).show();

                    }
                });


          btn_recusar.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  Emprestimo livro = livros.get(getLayoutPosition());

                  //mid.setIdLivro(livro.getIdLivro());


                  BaseDeDados baseDeDados = new BaseDeDados(context);
                  long
                          result = baseDeDados.UpStatus("INDISPONÍVEL", livro.getIdEmprestimo());

                  if (result != -1) {
                      AlertDialog.Builder builder = new AlertDialog.Builder(context);
                      builder.setTitle("LIVRO INDISPONÍVEL").setMessage("O usuário solicitante será informado sobre a situação do livro!")
                              .setPositiveButton("Ok", null).show();
                      btn_aceitar.setEnabled(false);
                      btn_recusar.setEnabled(false);
                  }else Toast.makeText(context, "Erro", Toast.LENGTH_LONG).show();



              }
          });



      }
    }



}