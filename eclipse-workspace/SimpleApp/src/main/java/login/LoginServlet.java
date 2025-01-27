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

        UserBean user = userDAO.authenticate(email, password); // Authentifier l'utilisateur

        if (user != null) {
            // Authentification réussie, on crée une session pour l'utilisateur
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            // Rediriger l'utilisateur vers la page d'accueil
            response.sendRedirect("home.jsp");
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
