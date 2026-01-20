package com.example.apps;

public class NotEntityPerson {
    private int id;
    private String name;
    private byte[] photo;
    private Byte[] foo;

    public NotEntityPerson() {
    }

    public NotEntityPerson(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Byte[] getFoo() {
        return foo;
    }

    public void setFoo(Byte[] foo) {
        this.foo = foo;
    }

}