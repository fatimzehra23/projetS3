package login;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            userDAO = new UserDaoImpl(daoFactory);
        } catch (DAOConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserBean user = userDAO.authenticate(email, password);  // Authentifier l'utilisateur

        if (user != null) {
            // Authentification réussie, on récupère le rôle de l'utilisateur
            String role = user.getRole();
            HttpSession session = request.getSession();
            session.setAttribute("email", email);  // Stocker l'email dans la session

            // Redirection en fonction du rôle
            if ("etudiant".equals(role)) {
                response.sendRedirect("studentDashboard.jsp");  // Page pour l'étudiant
            } else if ("technicien".equals(role)) {
                response.sendRedirect("technicianDashboard.jsp");  // Page pour le technicien
            } else if ("responsable".equals(role)) {
                response.sendRedirect("adminDashboard.jsp");  // Page pour le responsable
            } else {
                // Si le rôle n'est pas valide
                request.setAttribute("errorMessage", "Rôle inconnu.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Échec de l'authentification, afficher un message d'erreur
            request.setAttribute("errorMessage", "Email ou mot de passe incorrect.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Rediriger vers doPost pour gérer la requête GET
        doPost(request, response);
    }
}

