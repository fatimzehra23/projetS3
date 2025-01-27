package crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDaoImpl implements NoteDAO {
    private DAOFactory daoFactory;

    public NoteDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addNote(NoteBean note) {
        String sql = "INSERT INTO notes (title, content) VALUES (?, ?)";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, note.getTitle());
            preparedStatement.setString(2, note.getContent());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de la note", e);
        }
    }

    @Override
    public NoteBean getNoteById(int id) {
        String sql = "SELECT * FROM notes WHERE id = ?";
        NoteBean note = null;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                note = new NoteBean();
                note.setTitle(resultSet.getString("title"));
                note.setContent(resultSet.getString("content"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de la note", e);
        }
        return note;
    }

    @Override
    public List<NoteBean> getAllNotes() {
        String sql = "SELECT * FROM notes";
        List<NoteBean> notes = new ArrayList<>();

        try (Connection connection = daoFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                NoteBean note = new NoteBean();
                note.setTitle(resultSet.getString("title"));
                note.setContent(resultSet.getString("content"));
                notes.add(note);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des notes", e);
        }
        return notes;
    }

    @Override
    public void updateNote(NoteBean note) {
        String sql = "UPDATE notes SET title = ?, content = ? WHERE id = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, note.getTitle());
            preparedStatement.setString(2, note.getContent());
            preparedStatement.setInt(3, note.getId()); // Ajoutez un champ `id` dans NoteBean
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de la note", e);
        }
    }

    @Override
    public void deleteNote(int id) {
        String sql = "DELETE FROM notes WHERE id = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de la note", e);
        }
    }
}
