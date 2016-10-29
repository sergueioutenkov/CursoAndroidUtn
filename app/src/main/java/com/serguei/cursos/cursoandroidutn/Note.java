package com.serguei.cursos.cursoandroidutn;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by serguei on 29/10/16.
 */

public class Note extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String text;
    private String date;

    public Note() {
    }

    public Note(String text) {
        this.text = text;
        //Seteamos fecha de creacion de la nota
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy, HH:mm:ss");
        this.date = sdf.format(Calendar.getInstance().getTime());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
