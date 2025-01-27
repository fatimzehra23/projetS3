package reclamations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/interventionStdnt")
public class InterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private InterventionDAO interventionDAO;

    @Override
    public void init() throws ServletException {
        // Assuming DAOFactory is already set up in your project
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.interventionDAO = new InterventionDaoImpl(daoFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");  // Assuming the email is stored in session

        if (email == null) {
            response.sendRedirect("login.jsp");  // Redirect to login if the user is not logged in
            return;
        }

        List<InterventionBean> interventions = interventionDAO.getAllInterventions(email);
        request.setAttribute("interventionStdnt", interventions);
        
        // Forward to JSP page that displays the interventions
        request.getRequestDispatcher("interventionStdnt.jsp").forward(request, response);
        System.out.println("Email from session: " + email);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement form handling here for creating/updating interventions
        // This part can be expanded based on your requirements
    }
}



