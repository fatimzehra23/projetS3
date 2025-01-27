package technicien_fct;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/DisponibiliteServlet")
public class DisponibiliteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DisponibiliteDAO disponibiliteDAO;

    @Override
    public void init() throws ServletException {
        // Étape de débogage pour vérifier que l'initialisation se fait correctement
        System.out.println("Initialisation de DisponibiliteServlet...");
        DAOFactory daoFactory = DAOFactory.getInstance();
        disponibiliteDAO = daoFactory.getDisponibiliteDAO();
        System.out.println("DisponibiliteDAO initialisé.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        System.out.println("Début de la méthode doGet.");

        // Vérification de la session et de l'email
        if (session == null || session.getAttribute("email") == null) {
            System.out.println("Aucune session active ou aucun email trouvé, redirection vers login.jsp.");
            response.sendRedirect("login.jsp");
            return;
        }

        String email = (String) session.getAttribute("email");
        System.out.println("Email récupéré depuis la session : " + email);

        try {
            // Récupérer le technicien et sa disponibilité à partir de l'email
            TechnicienFctBean technicien = disponibiliteDAO.getTechnicienByEmail(email);
            Disponibilite disponibilite = disponibiliteDAO.getDisponibiliteByEmail(email);
            System.out.println("Récupération du technicien et de sa disponibilité...");


            // Débogage pour vérifier les données
            System.out.println("Technicien : " + technicien);
            System.out.println("Disponibilité : " + disponibilite);

            // Ajouter les données à la requête
            request.setAttribute("technicien", technicien);
            request.setAttribute("disponibilite", disponibilite);

            // Transférer la requête vers la JSP
            System.out.println("Transfert vers la page echnicianDashboard.jsp.");
            request.getRequestDispatcher("technicianDashboard.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des informations : " + e.getMessage());
            throw new ServletException("Erreur lors de la récupération des informations.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        System.out.println("Début de la méthode doPost.");

        // Vérification de la session et de l'email
        if (session == null || session.getAttribute("email") == null) {
            System.out.println("Aucune session active ou aucun email trouvé, redirection vers login.jsp.");
            response.sendRedirect("login.jsp");
            return;
        }

        String email = (String) session.getAttribute("email");
        String disponibiliteStr = request.getParameter("disponibilite");

        System.out.println("Email récupéré depuis la session : " + email);
        System.out.println("Valeur de disponibilité reçue : " + disponibiliteStr);

        try {
            // Vérifier si la valeur est valide avant de la convertir en énumération
            if (disponibiliteStr != null) {
                try {
                    // Conversion en majuscules pour éviter les erreurs de casse
                    Disponibilite disponibilite = Disponibilite.valueOf(disponibiliteStr.toUpperCase());
                    System.out.println("Disponibilité convertie en énumération : " + disponibilite);
                    disponibiliteDAO.updateDisponibiliteByEmail(email, disponibilite);
                    System.out.println("Disponibilité mise à jour pour l'email : " + email);
                } catch (IllegalArgumentException e) {
                    // Gestion de l'erreur si la valeur est incorrecte
                    System.out.println("Valeur de disponibilité invalide reçue : " + disponibiliteStr);
                    throw new ServletException("Valeur de disponibilité invalide : " + disponibiliteStr, e);
                }
            } else {
                System.out.println("La disponibilité est manquante.");
                throw new ServletException("La disponibilité est manquante.");
            }

            // Redirection vers la servlet pour recharger les données
            System.out.println("Redirection vers DisponibiliteServlet pour recharger les données.");
            response.sendRedirect("DisponibiliteServlet");

        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour de la disponibilité : " + e.getMessage());
            throw new ServletException("Erreur lors de la mise à jour de la disponibilité.", e);
        }
    }
}