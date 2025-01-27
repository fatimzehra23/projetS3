package technicien;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/TechnicienServlet")
public class TechnicienServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TechnicienDAO technicienDAO;

    @Override
    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        technicienDAO = new TechnicienDaoImpl(daoFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null || action.equals("list")) {
                // Action "list" ou pas d'action spécifiée : afficher la liste des techniciens
                listTechniciens(request, response);
            } else {
                switch (action) {
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deleteTechnicien(request, response);
                        break;
                    default:
                        listTechniciens(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException("Erreur lors du traitement de la requête : " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                response.sendRedirect("TechnicienServlet?action=list");  // Redirection vers la liste des techniciens
            } else {
                switch (action) {
                    case "add":
                        addTechnicien(request, response);
                        break;
                    case "update":
                        updateTechnicien(request, response);
                        break;  
                    default:
                        response.sendRedirect("TechnicienServlet?action=list");  // Redirection vers la liste des techniciens
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException("Erreur lors du traitement de la requête : " + e.getMessage(), e);
        }
    }

    private void listTechniciens(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TechnicienBean> techniciens = technicienDAO.getAllTechniciens();
        request.setAttribute("techniciens", techniciens);
        RequestDispatcher dispatcher = request.getRequestDispatcher("techniciens.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTech = Integer.parseInt(request.getParameter("idTech"));
        TechnicienBean existingTechnicien = technicienDAO.getTechnicienById(idTech);
        request.setAttribute("technicien", existingTechnicien);
        RequestDispatcher dispatcher = request.getRequestDispatcher("technicien-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addTechnicien(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            System.out.println("Début de la méthode addTechnicien");

            // Extraction des paramètres depuis le formulaire
            String nomTech = request.getParameter("nomTech");
            String specialite = request.getParameter("specialite");
            String disponibilite = request.getParameter("disponibilite");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");

            // Afficher les valeurs récupérées
            System.out.println("Nom: " + nomTech);
            System.out.println("Spécialité: " + specialite);
            System.out.println("Disponibilité: " + disponibilite);
            System.out.println("Email: " + email);
            System.out.println("Téléphone: " + telephone);

            // Créer un nouvel objet Technicien
            TechnicienBean technicien = new TechnicienBean();
            technicien.setNomTech(nomTech);
            technicien.setSpecialite(specialite);
            technicien.setDisponibilite(disponibilite);
            technicien.setEmail(email);
            technicien.setTelephone(telephone);

            // Log de l'objet Technicien
            System.out.println("TechnicienBean créé : " + technicien);

            // Appeler la méthode DAO pour ajouter le technicien
            technicienDAO.addTechnicien(technicien);
            System.out.println("Technicien ajouté avec succès");

            // Redirection vers la liste des techniciens
            response.sendRedirect("TechnicienServlet?action=list");
        } catch (Exception e) {
            // Log de l'erreur
            System.err.println("Erreur lors de l'ajout du technicien : " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("TechnicienServlet?action=list&error=addFailed");
        }
    }


    private void updateTechnicien(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int idTech = Integer.parseInt(request.getParameter("idTech"));
            String nomTech = request.getParameter("nomTech");
            String specialite = request.getParameter("specialite");
            String disponibilite = request.getParameter("disponibilite");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");

            System.out.println("ID Tech: " + idTech);
            System.out.println("Nom: " + nomTech);
            System.out.println("Spécialité: " + specialite);
            System.out.println("Disponibilité: " + disponibilite);
            System.out.println("Email: " + email);
            System.out.println("Téléphone: " + telephone);

            TechnicienBean updatedTechnicien = new TechnicienBean(idTech, nomTech, specialite, disponibilite, email, telephone);
            technicienDAO.updateTechnicien(updatedTechnicien);

            response.sendRedirect("TechnicienServlet?action=list");  // Redirection vers la liste des techniciens
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("TechnicienServlet?action=list&error=updateFailed");
        }
    }


    private void deleteTechnicien(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idTech = Integer.parseInt(request.getParameter("idTech"));
        technicienDAO.deleteTechnicien(idTech);
        response.sendRedirect("TechnicienServlet?action=list");  // Redirection vers la liste des techniciens
    }
}



