package com.example.realm.m_realm;

import io.realm.RealmObject;

public class Spacecraft extends RealmObject {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;
}
