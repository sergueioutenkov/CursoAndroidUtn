package com.serguei.cursos.cursoandroidutn;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private EditText addNote;
    private RecyclerView notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Obtenemos instancia de Realm
        realm = Realm.getDefaultInstance();

        addNote = (EditText) findViewById(R.id.add_note_text);
        notesList = (RecyclerView) findViewById(R.id.notes_list);
        notesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_note_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addNote.getText().length() > 0) {
                    insertNote(addNote.getText().toString());
                    addNote.setText("");
                    Snackbar.make(view, "Note added!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        loadNotes();
    }

    private void insertNote(final String noteText) {
        //Creo una transaccion y la ejecuto
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //Simplemente creo una nota
                Note note = new Note(noteText);
                //Con este metodo la nota se guarda en Realm
                realm.copyToRealm(note);
            }
        });
    }

    private void loadNotes() {
        //Query para obtener todas las notas
        RealmResults<Note> notes = realm.where(Note.class).findAll();

        //Solamente seteamos el adapter y lo bindeamos al recyclerview
        NotesAdapter notesAdapter = new NotesAdapter(notes, this);
        notesList.setAdapter(notesAdapter);
    }

}
