package reclamations;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/reclamations")
public class ReclamationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReclamationDAO reclamationDAO;

    @Override	
    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        reclamationDAO = new ReclamationDaoImpl(daoFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if (action == null) {
                listReclamations(request, response);
            } else {
                switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "insert":
                        insertReclamation(request, response);
                        break;
                    case "delete":
                        deleteReclamation(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "update":
                        updateReclamation(request, response);
                        break;
                    case "search":
                        searchReclamation(request, response);
                        break;
                    default:
                        listReclamations(request, response);
                        break;
                }
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listReclamations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("email");
        
        if (email != null) {
            List<ReclamationBean> listReclamation = reclamationDAO.getAllReclamations(email);
            request.setAttribute("listReclamation", listReclamation);
        } else {
            response.sendRedirect("login.jsp");
            return;
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("reclamations.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("reclamation-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertReclamation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sujet = request.getParameter("sujet");
        String description = request.getParameter("description");
        String email = (String) request.getSession().getAttribute("email");
        java.util.Date dateCreation = new java.util.Date();
        String batiment = request.getParameter("batiment");
        String etage = request.getParameter("etage");
        int numChambre = Integer.parseInt(request.getParameter("numChambre"));

        ReclamationBean newReclamation = new ReclamationBean();
        newReclamation.setSujet(sujet);
        newReclamation.setDescription(description);
        newReclamation.setEmail(email);
        newReclamation.setDateCreation(dateCreation);
        newReclamation.setBatiment(batiment);
        newReclamation.setEtage(etage);
        newReclamation.setNumChambre(numChambre);

        reclamationDAO.addReclamation(newReclamation);
        request.getSession().setAttribute("message", "Réclamation ajoutée avec succès.");
        response.sendRedirect("reclamations");
    }

    private void deleteReclamation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idRecl = Integer.parseInt(request.getParameter("idRecl"));
        reclamationDAO.deleteReclamation(idRecl);
        response.sendRedirect("reclamations");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idRecl = Integer.parseInt(request.getParameter("idRecl"));
        ReclamationBean existingReclamation = reclamationDAO.getReclamationById(idRecl);
        RequestDispatcher dispatcher = request.getRequestDispatcher("reclamation-form.jsp");
        request.setAttribute("reclamation", existingReclamation);
        dispatcher.forward(request, response);
    }

    private void updateReclamation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idRecl = Integer.parseInt(request.getParameter("idRecl"));
        String sujet = request.getParameter("sujet");
        String description = request.getParameter("description");
        String email = (String) request.getSession().getAttribute("email");
        String batiment = request.getParameter("batiment");
        String etage = request.getParameter("etage");
        int numChambre = Integer.parseInt(request.getParameter("numChambre"));

        ReclamationBean reclamation = new ReclamationBean(idRecl, sujet, description, new java.util.Date(), email, batiment, etage, numChambre);
        reclamationDAO.updateReclamation(reclamation);
        response.sendRedirect("reclamations");
    }

    private void searchReclamation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        List<ReclamationBean> searchResults = reclamationDAO.searchReclamations(searchQuery);
        request.setAttribute("listReclamation", searchResults);
        RequestDispatcher dispatcher = request.getRequestDispatcher("reclamations.jsp");
        dispatcher.forward(request, response);
    }
}

