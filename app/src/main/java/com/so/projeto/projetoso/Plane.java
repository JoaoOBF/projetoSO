package com.so.projeto.projetoso;

import java.util.Date;

public class Plane {

    String name;
    String status;
    Date datetime;

    public Plane(String name, String status){

        this.name = name;
        this.status = status;

    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatetime() {
        return datetime;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
