package com.example.rodolfo.mudioapp.Itens;

/**
 * Created by Rodolfo on 24/07/2015.
 */
public class Exercicio {
    private String mExNome;
    private int mExSeries;
    private int mExRepeticoes;
    private int mPeso;
    private int mGoal;

    public Exercicio(String mExNome, int mExSeries, int mExRepeticoes, int mPeso, int mGoal) {
        this.mExNome = mExNome;
        this.mExSeries = mExSeries;
        this.mExRepeticoes = mExRepeticoes;
        this.mPeso = mPeso;
        this.mGoal = mGoal;
    }

    public String getmExNome() {
        return mExNome;
    }

    public void setmExNome(String mExNome) {
        this.mExNome = mExNome;
    }

    public int getmExSeries() {
        return mExSeries;
    }

    public void setmExSeries(int mExSeries) {
        this.mExSeries = mExSeries;
    }

    public int getmExRepeticoes() {
        return mExRepeticoes;
    }

    public void setmExRepeticoes(int mExRepeticoes) {
        this.mExRepeticoes = mExRepeticoes;
    }

    public int getmPeso() {
        return mPeso;
    }

    public void setmPeso(int mPeso) {
        this.mPeso = mPeso;
    }

    public int getmGoal() {
        return mGoal;
    }

    public void setmGoal(int mGoal) {
        this.mGoal = mGoal;
    }
}
