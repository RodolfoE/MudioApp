package com.example.rodolfo.mudioapp.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodolfo.mudioapp.Itens.Exercicio;
import com.example.rodolfo.mudioapp.R;
import com.example.rodolfo.mudioapp.adapter.DiaAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dia1 extends Fragment {

    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dia1, container, false);

        setUpRecyclerView(view);

        return view;
    }

    private void setUpRecyclerView(View v){

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.setAdapter(new DiaAdapter(getLista(), getActivity().getApplicationContext(), onClickListener(), getActivity().getPreferences(Context.MODE_PRIVATE), DiaAdapter.DiaEnum.DIA1.toString()));

    }

    private DiaAdapter.DiaListener onClickListener(){
        return new DiaAdapter.DiaListener() {
            @Override
            public void onClick(View view, int idx, int IdItemClicked) {
                if (IdItemClicked == R.id.aumentar_peso){
                    int aux = Integer.parseInt( ((TextView) view.findViewById(R.id.dia_peso)).getText().toString() );
                    aux++;
                    ((TextView) view.findViewById(R.id.dia_peso)).setText(aux + "");

                    SharedPreferences ultimoPeso = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = ultimoPeso.edit();
                    editor.putInt(DiaAdapter.DiaEnum.DIA1.toString() + idx, aux);
                    editor.commit();
                } else if (IdItemClicked == R.id.diminuir_peso){
                    int aux = Integer.parseInt( ((TextView) view.findViewById(R.id.dia_peso)).getText().toString() );
                    aux--;
                    ((TextView) view.findViewById(R.id.dia_peso)).setText(aux + "");

                    SharedPreferences ultimoPeso = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = ultimoPeso.edit();
                    editor.putInt(DiaAdapter.DiaEnum.DIA1.toString() + idx, aux);
                    editor.commit();
                }
            }
        };
    }

    private List<Exercicio> getLista(){
        ArrayList<Exercicio> ex = new ArrayList<Exercicio>();

        /* ficha do gabriel
        //nome do ex - series - repeticao -
        ex.add(new Exercicio("Supino Reto", 4, 10, 6, 10));
        ex.add(new Exercicio("Crucifixo Voador", 4, 10, 6, 10));
        ex.add(new Exercicio("Supino 45 com alter", 3, 10, 6, 10));
        ex.add(new Exercicio("Supino declinado", 3, 10, 6, 10));
        ex.add(new Exercicio("Triceps barra fixa", 3, 12, 6, 10));
        ex.add(new Exercicio("Francês", 4, 10, 6, 10));
        ex.add(new Exercicio("Triceps corda", 4, 10, 6, 10));
        */



        /* minha ficha - rodolfo */
        ex.add(new Exercicio("Desenvolvimento no aparelho", 3, 12, 6, 10));
        ex.add(new Exercicio("Elevação mixta", 3, 10, 6, 10));
        ex.add(new Exercicio("Crucifixo invertido", 3, 8, 6, 10));
        ex.add(new Exercicio("Leg press 45", 3, 12, 6, 10));
        ex.add(new Exercicio("Banco Extensor", 3, 10, 6, 10));
        ex.add(new Exercicio("Mesa Flexora", 3, 10, 6, 10));
        ex.add(new Exercicio("Abdutor com troncoinclinado", 3, 8, 6, 10));
        ex.add(new Exercicio("Banco abdutor", 3, 15, 6, 10));
        ex.add(new Exercicio("Flrxao plantar unilateral", 3, 15, 6, 10));

        return ex;
    }

}
