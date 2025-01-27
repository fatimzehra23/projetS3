package interventions;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/choisirTechnicien")
public class TechnicienRServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TechnicienDAO technicienDAO;

    @Override
    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        technicienDAO = new TechnicienDaoImpl(daoFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String specialite = request.getParameter("specialite");
        List<TechnicienBean> techniciens = null;

        try {
            if (specialite != null && !specialite.isEmpty()) {
                techniciens = technicienDAO.getTechniciensBySpecialite(specialite);
            } else {
                techniciens = technicienDAO.getAllTechniciens();
            }

            // Vérifiez que la liste de techniciens est récupérée correctement
            if (techniciens != null && !techniciens.isEmpty()) {
                request.setAttribute("techniciens", techniciens);
            } else {
                request.setAttribute("message", "Aucun technicien trouvé.");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("choisirTechnicien.jsp");
            dispatcher.forward(request, response);
        } catch (DAOException e) {
            throw new ServletException("Erreur lors de la récupération des techniciens.", e);
        }
    }
}





