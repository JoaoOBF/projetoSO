package com.so.projeto.projetoso;

import java.util.Date;

public class Plane {

    String name;
    String status;

    public Plane(String name, String status) {

        this.name = name;
        this.status = status;

    }


    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
