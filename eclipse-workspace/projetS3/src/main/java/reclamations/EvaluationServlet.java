package reclamations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EvaluationServlet")
public class EvaluationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entrée dans doGet de EvaluationServlet");

        // Récupération de l'ID de l'intervention depuis les paramètres
        String idIntervStr = request.getParameter("interventionId"); // Correspond au paramètre dans l'URL
        if (idIntervStr == null || idIntervStr.trim().isEmpty()) {
            System.out.println("ID d'intervention manquant ou invalide");
            request.setAttribute("errorMessage", "L'ID de l'intervention est requis !");

            request.getRequestDispatcher("evaluation.jsp").forward(request, response);
            return;
        }

        try {
            int idInterv = Integer.parseInt(idIntervStr);
            System.out.println("ID d'intervention valide : " + idInterv);

            // Ajouter des informations pertinentes à transmettre à la JSP si nécessaire
            request.setAttribute("idInterv", idInterv);

            // Rediriger vers la page du formulaire d'évaluation
            request.getRequestDispatcher("evaluation.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println("Erreur : ID d'intervention invalide");
            e.printStackTrace();
            request.setAttribute("errorMessage", "L'ID de l'intervention doit être un nombre valide !");
            request.getRequestDispatcher("evaluation.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Début du traitement dans EvaluationServlet");

        // 1. Obtention de l'instance de DAOFactory
        DAOFactory daoFactory = DAOFactory.getInstance();
        EvaluationDAO evaluationDao = daoFactory.getEvaluationDao();
        InterventionDAO interventionDao = daoFactory.getInterventionDao();

        // 2. Récupération des paramètres
        String idIntervStr = request.getParameter("idInterv");
        String evaluationStr = request.getParameter("evaluation");
        String commentaire = request.getParameter("commentaire");

        // 3. Vérification des paramètres
        if (idIntervStr == null || idIntervStr.trim().isEmpty() ||
                evaluationStr == null || evaluationStr.trim().isEmpty() ||
                commentaire == null || commentaire.trim().length() < 5) {
            System.out.println("Validation échouée : Champs manquants ou invalides");
            request.setAttribute("errorMessage", "Tous les champs sont requis et doivent être valides !");
            request.getRequestDispatcher("evaluation.jsp").forward(request, response);
            return;
        }

        try {
            System.out.println("Paramètres reçus : idInterv=" + idIntervStr + ", evaluation=" + evaluationStr + ", commentaire=" + commentaire);

            int idInterv = Integer.parseInt(idIntervStr);
            int evaluation = Integer.parseInt(evaluationStr);

            System.out.println("Conversion des paramètres réussie : idInterv=" + idInterv + ", evaluation=" + evaluation);

            // 4. Enregistrer l'évaluation dans la base de données
            System.out.println("Tentative d'enregistrement de l'évaluation dans la base de données...");
            evaluationDao.enregistrerEvaluation(idInterv, evaluation, commentaire);

            System.out.println("Évaluation enregistrée avec succès dans la base de données.");

            // 5. Redirection vers une page de succès
            request.setAttribute("successMessage", "Évaluation envoyée avec succès !");
            request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            System.out.println("Erreur : Les paramètres idInterv ou evaluation ne sont pas des nombres valides.");
            e.printStackTrace();
            request.setAttribute("errorMessage", "L'évaluation et l'ID d'intervention doivent être des nombres valides !");
            request.getRequestDispatcher("evaluation.jsp").forward(request, response);
        } catch (SQLException e) {
            System.out.println("Erreur SQL lors de l'enregistrement de l'évaluation.");
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur lors de l'enregistrement de l'évaluation !");
            request.getRequestDispatcher("evaluation.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Erreur inattendue.");
            e.printStackTrace();
            request.setAttribute("errorMessage", "Une erreur inattendue s'est produite !");
            request.getRequestDispatcher("evaluation.jsp").forward(request, response);
        }
    }
}

