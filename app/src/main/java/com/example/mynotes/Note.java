package com.example.mynotes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
/*
   author:   KumarAnkit81
   project:  myNotes
   language: java
   database: realm
 */
public class Note extends RealmObject {
    @PrimaryKey int id;
    String Title;
    String Description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
