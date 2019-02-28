package com.so.projeto.projetoso;

import java.util.ArrayList;

public class Management {

    long pousoTime = 6000;
    long decolagemTime = 4000;

    ArrayList<Plane> pousosArray = new ArrayList<>();
    ArrayList<Plane> decolagensArray = new ArrayList<>();

    public ArrayList<Plane> getDecolagensArray() {
        return decolagensArray;
    }

    public ArrayList<Plane> getPousosArray() {
        return pousosArray;
    }

    public long getDecolagemTime() {
        return decolagemTime;
    }

    public long getPousoTime() {
        return pousoTime;
    }

    public void setDecolagemTime(long decolagemTime) {
        this.decolagemTime = decolagemTime;
    }

    public void setPousoTime(long pousoTime) {
        this.pousoTime = pousoTime;
    }

    public void setDecolagensArray(ArrayList<Plane> decolagensArray) {
        this.decolagensArray = decolagensArray;
    }

    public void setPousosArray(ArrayList<Plane> pousosArray) {
        this.pousosArray = pousosArray;
    }
}
