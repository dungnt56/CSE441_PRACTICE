package com.dungnt.thuc_hanh_03.entity;

import java.io.Serializable;

public class Fullname implements Serializable {
    String first;
    String last;
    String midd;

    public Fullname() {
    }

    public String getFirstName() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLastName() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getMiddleName() {
        return midd;
    }

    public void setMidd(String midd) {
        this.midd = midd;
    }
}
