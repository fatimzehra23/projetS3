package crud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/notes")
public class NoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoteDAO noteDAO;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance(); // Crée une instance de DAOFactory
        noteDAO = new NoteDaoImpl(daoFactory); // Injecte NoteDAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                listNotes(request, response);
            } else {
                switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deleteNote(request, response);
                        break;
                    default:
                        listNotes(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                listNotes(request, response);
            } else {
                switch (action) {
                    case "insert":
                        insertNote(request, response);
                        break;
                    case "update":
                        updateNote(request, response);
                        break;
                    default:
                        listNotes(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listNotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<NoteBean> notes = noteDAO.getAllNotes();
        request.setAttribute("notes", notes);
        request.getRequestDispatcher("notes.jsp").forward(request, response); // Forward vers une page JSP
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("note-form.jsp").forward(request, response); // Formulaire pour une nouvelle note
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        NoteBean note = noteDAO.getNoteById(id);
        request.setAttribute("note", note);
        request.getRequestDispatcher("note-form.jsp").forward(request, response); // Pré-remplir le formulaire
    }

    private void insertNote(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        NoteBean newNote = new NoteBean();
        newNote.setTitle(title);
        newNote.setContent(content);

        noteDAO.addNote(newNote);
        response.sendRedirect("notes"); // Redirige vers la liste des notes
    }

    private void updateNote(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        NoteBean note = new NoteBean();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);

        noteDAO.updateNote(note);
        response.sendRedirect("notes"); // Redirige vers la liste des notes
    }

    private void deleteNote(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        noteDAO.deleteNote(id);
        response.sendRedirect("notes"); // Redirige vers la liste des notes
    }
}
