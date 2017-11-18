package com.serguei.cursos.cursoandroidutn;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    List<Note> notes;
    Context context;

    public NotesAdapter(RealmResults<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, null, false));
    }

    @Override
    public void onBindViewHolder(final NoteViewHolder holder, int position) {
        final Note note = notes.get(position);
        holder.noteTitle.setText(note.getText());
        holder.noteDate.setText(note.getDate());

        holder.noteTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        //Para borrar solamente llamamos este metodo
                        note.deleteFromRealm();

                        notifyItemRemoved(holder.getAdapterPosition());
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView noteTitle;
        TextView noteDate;

        public NoteViewHolder(View itemView) {
            super(itemView);
            noteTitle = (TextView) itemView.findViewById(R.id.note_text);
            noteDate = (TextView) itemView.findViewById(R.id.note_date);
        }
    }
}
