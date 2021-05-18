package com.example.lendbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lendbook.banco.BaseDeDados;
import com.example.lendbook.objetos.Emprestimo;
import com.example.lendbook.objetos.User;

import java.util.List;

public class ListarLivrosSolicitados extends RecyclerView.Adapter<ListarLivrosSolicitados.MyViewHolder> {

    Context context;
    private static List<Emprestimo> livros;

    private static User mid;

    public ListarLivrosSolicitados(Context context, List<Emprestimo> livrosU, User users){

        this.context = context;
        this.livros = livrosU;

        this.mid = users;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;


            view = LayoutInflater.from(context).inflate(R.layout.dados_pedidos, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view, parent.getContext());

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.nomeLivroPedido.setText("Título: "+livros.get(position).getNomeLivro());
        holder.nomeDono.setText("Propiretário(a): "+livros.get(position).getNomeDno());
        holder.status_2.setText("Status: "+livros.get(position).getStatus());
        holder.imgPedido.setImageResource(R.drawable.ic_launcher_1_foreground);



            }


    @Override
    public int getItemCount() {
        return livros.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView  imgPedido;
        private TextView  nomeDono, status_2, nomeLivroPedido;





      public MyViewHolder (@NonNull final View itemView, final Context context){
          super(itemView);


          imgPedido = (ImageView) itemView.findViewById(R.id.imgPedido);



          nomeDono = (TextView) itemView.findViewById(R.id.txtPusernome);
          nomeLivroPedido = (TextView) itemView.findViewById(R.id.txtPnomelivro);
          status_2 = (TextView) itemView.findViewById(R.id.statusPedido);








      }
    }



}