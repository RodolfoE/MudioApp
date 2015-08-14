package com.example.rodolfo.mudioapp.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodolfo.mudioapp.Itens.Exercicio;
import com.example.rodolfo.mudioapp.R;

import java.util.List;

/**
 * Created by Rodolfo on 24/07/2015.
 */
public class DiaAdapter extends RecyclerView.Adapter<DiaAdapter.myViewHolder> {
    private List<Exercicio> mExercicios;
    private Context mCtx;
    private DiaListener mOnClickListener;
    private SharedPreferences mPreferences;
    private String mDia;

    public interface DiaListener{
        public void onClick(View view, int idx, int idItemClicked);
    }

    public DiaAdapter(List<Exercicio> mExercicios, Context mCtx, DiaListener mOnClickListener, SharedPreferences preferences, String dia) {
        this.mExercicios = mExercicios;
        this.mCtx = mCtx;
        this.mOnClickListener = mOnClickListener;
        this.mPreferences = preferences;
        this.mDia = dia;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.adapter_dia, null);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final myViewHolder viewHolder, final int i) {
        Exercicio item = mExercicios.get(i);

        int peso = mPreferences.getInt(mDia + i, 0);


        viewHolder.titulo.setText(item.getmExNome());
        viewHolder.series.setText(item.getmExSeries() + "");
        viewHolder.repeticoes.setText(item.getmExRepeticoes() + "");
        viewHolder.peso.setText(peso + "" );

        viewHolder.aumentarPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onClick(viewHolder.itemView, i, R.id.aumentar_peso);
            }
        });

        viewHolder.diminuirPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onClick(viewHolder.itemView, i, R.id.diminuir_peso);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mExercicios.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        TextView titulo, series, repeticoes, peso;
        ImageView aumentarPeso, diminuirPeso;

        public myViewHolder(View itemView) {
            super(itemView);

            titulo = (TextView) itemView.findViewById(R.id.dia_nome_ex);
            series = (TextView) itemView.findViewById(R.id.dia_series);
            repeticoes = (TextView) itemView.findViewById(R.id.dia_repeticoes);
            peso = (TextView) itemView.findViewById(R.id.dia_peso);

            aumentarPeso = (ImageView) itemView.findViewById(R.id.aumentar_peso);
            diminuirPeso = (ImageView) itemView.findViewById(R.id.diminuir_peso);
        }
    }

    public  enum DiaEnum{
        DIA1, DIA2, DIA3, DIA4, DIA5;
    }
}
