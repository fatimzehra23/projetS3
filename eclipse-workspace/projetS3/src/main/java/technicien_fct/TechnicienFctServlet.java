package technicien_fct;

import interventions.InterventionBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/technicienInterventions")
public class TechnicienFctServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TechnicienFctDAO technicienFctDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialisation du DAO
        DAOFactory daoFactory = DAOFactory.getInstance();
        technicienFctDAO = new TechnicienFctDaoImpl(daoFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'email du technicien depuis la session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        if (email != null) {
            // Récupérer les interventions du technicien en fonction de son email
            List<InterventionBean> interventions = technicienFctDAO.getInterventionsByTechnician(email);

            // Passer la liste des interventions à la page JSP
            request.setAttribute("interventions", interventions);
            request.getRequestDispatcher("technicienInterventions.jsp").forward(request, response);
        } else {
            // Rediriger vers la page de login si l'email n'est pas trouvé
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID de l'intervention et la nouvelle date de terminaison
        int idInterv = Integer.parseInt(request.getParameter("idInterv"));
        java.sql.Date dateTerminaison = java.sql.Date.valueOf(request.getParameter("dateTerminaison"));
        String statut = request.getParameter("statut");

        // Créer un objet InterventionBean pour mettre à jour l'intervention
        InterventionBean intervention = new InterventionBean();
        intervention.setIdInterv(idInterv);
        intervention.setDateTerminaison(dateTerminaison);
        intervention.setStatut(statut);

        try {
            // Mettre à jour l'intervention
            technicienFctDAO.updateIntervention(intervention);
            // Rediriger vers la liste des interventions après la mise à jour
            response.sendRedirect("technicienInterventions");
        } catch (Exception e) {
            // En cas d'erreur, afficher un message d'erreur
            request.setAttribute("errorMessage", "Erreur lors de la mise à jour de l'intervention.");
            request.getRequestDispatcher("technicienInterventions.jsp").forward(request, response);
        }
    }
}
