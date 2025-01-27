package crud;

import java.util.List;

public interface NoteDAO {

    // Ajouter une nouvelle note
    void addNote(NoteBean note);

    // Récupérer une note par son ID
    NoteBean getNoteById(int id);

    // Récupérer toutes les notes
    List<NoteBean> getAllNotes();

    // Mettre à jour une note existante
    void updateNote(NoteBean note);

    // Supprimer une note par son ID
    void deleteNote(int id);
}
