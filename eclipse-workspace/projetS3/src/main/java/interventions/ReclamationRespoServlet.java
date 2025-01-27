package interventions;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/reclamationsRespo")
public class ReclamationRespoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReclamationDAO reclamationDAO;
    private TechnicienDAO technicienDAO; // DAO pour gérer les techniciens

    @Override
    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        reclamationDAO = new ReclamationDaoImpl(daoFactory);
        technicienDAO = new TechnicienDaoImpl(daoFactory); // Initialisation du DAO des techniciens
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                listReclamations(request, response);
            } else {
                switch (action) {
                    case "delete":
                        deleteReclamation(request, response);
                        break;
                    case "prepareIntervention":
                        prepareInterventionForm(request, response);
                        break;
                    case "filterTechniciens":
                        filterTechniciens(request, response);
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
        String action = request.getParameter("action");

        if ("addIntervention".equals(action)) {
            addIntervention(request, response);
        } else {
            doGet(request, response);
        }
    }

    private void listReclamations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ReclamationBean> listReclamation = reclamationDAO.getAllReclamations();
        request.setAttribute("listReclamation", listReclamation);

        RequestDispatcher dispatcher = request.getRequestDispatcher("reclamationsRespo.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteReclamation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idRecl = Integer.parseInt(request.getParameter("idRecl"));

        try {
            reclamationDAO.deleteReclamation(idRecl);
            request.getSession().setAttribute("message", "Réclamation supprimée avec succès.");
            response.sendRedirect("reclamationsRespo");
        } catch (DAOException e) {
            throw new ServletException("Erreur lors de la suppression de la réclamation.", e);
        }
    }

    protected void prepareInterventionForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idRecl = Integer.parseInt(request.getParameter("idRecl"));
        try {
            ReclamationBean reclamation = reclamationDAO.getReclamationDetailsForIntervention(idRecl);
            if (reclamation != null) {
                request.setAttribute("reclamation", reclamation);

                // Redirection vers la liste des techniciens
                List<TechnicienBean> allTechniciens = technicienDAO.getAllTechniciens();
                request.setAttribute("techniciens", allTechniciens);

                RequestDispatcher dispatcher = request.getRequestDispatcher("select-technicien.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("reclamationsRespo?action=list&error=ReclamationNotFound");
            }
        } catch (DAOException e) {
            throw new ServletException("Erreur lors de la préparation du formulaire d'intervention", e);
        }
    }

    protected void filterTechniciens(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String specialite = request.getParameter("specialite");

        try {
            List<TechnicienBean> filteredTechniciens = technicienDAO.getTechniciensBySpecialite(specialite);
            request.setAttribute("techniciens", filteredTechniciens);
            RequestDispatcher dispatcher = request.getRequestDispatcher("select-technicien.jsp");
            dispatcher.forward(request, response);
        } catch (DAOException e) {
            throw new ServletException("Erreur lors du filtrage des techniciens", e);
        }
    }

    protected void addIntervention(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String priority = request.getParameter("priority");
    	int idRecl = Integer.parseInt(request.getParameter("idRecl"));
        String description = request.getParameter("description");
        String batiment = request.getParameter("batiment");
        String etage = request.getParameter("etage");
        int numChambre = Integer.parseInt(request.getParameter("numChambre"));
        String nomTech = request.getParameter("nomTech");
        String statut = "en attente"; 
        String email = request.getParameter("email");

        try {
            InterventionBean intervention = new InterventionBean();
            intervention.setPriority(priority);
            intervention.setDescription(description);
            intervention.setBatiment(batiment);
            intervention.setEtage(etage);
            intervention.setNumChambre(numChambre);
            intervention.setNomTech(nomTech);
            intervention.setStatut(statut);
            intervention.setEmail(email);

            reclamationDAO.createIntervention(intervention);
            request.getSession().setAttribute("message", "Intervention ajoutée avec succès.");
            response.sendRedirect("reclamationsRespo?action=list");
        } catch (DAOException e) {
            throw new ServletException("Erreur lors de l'ajout de l'intervention", e);
        }
    }
}

