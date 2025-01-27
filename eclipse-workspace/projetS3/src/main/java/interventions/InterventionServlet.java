package interventions;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/interventions")
public class InterventionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private InterventionDAO interventionDAO;

    @Override
    public void init() throws ServletException {
        this.interventionDAO = new InterventionDaoImpl(DAOFactory.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Récupérer le paramètre de statut (s'il est présent)
            String statut = request.getParameter("statut");

            List<InterventionBean> interventions;

            if ("terminee".equalsIgnoreCase(statut)) {
                // Récupérer uniquement les interventions terminées
                interventions = interventionDAO.getTerminatedInterventions();
            } else {
                // Par défaut, récupérer toutes les interventions
                interventions = interventionDAO.getAllInterventions();
            }

            // Ajouter les interventions à la requête
            request.setAttribute("interventions", interventions);

            // Rediriger vers la page JSP appropriée
            RequestDispatcher dispatcher = request.getRequestDispatcher("/interventions.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving interventions.");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                addIntervention(request, response);
            } else if ("update".equals(action)) {
                updateIntervention(request, response);
            } else if ("delete".equals(action)) {
                deleteIntervention(request, response);
            } else if ("assignTechnician".equals(action)) {
                assignTechnicianToIntervention(request, response);
            } else {
                response.sendRedirect("interventions");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing the request.");
        }
    }

    private void addIntervention(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String description = request.getParameter("description");
            String dateCreation = request.getParameter("dateCreation");
            String dateTerminaison = request.getParameter("dateTerminaison");
            String nomTech = request.getParameter("nomTech"); // Ensure this is correctly captured
            String priority = request.getParameter("priority");
            String batiment = request.getParameter("batiment");
            String etage = request.getParameter("etage");
            int numChambre = Integer.parseInt(request.getParameter("numChambre"));
            String email = request.getParameter("email");
            String statut = request.getParameter("statut");

            // Create an InterventionBean and set the values
            InterventionBean intervention = new InterventionBean();
            intervention.setDescription(description);
            intervention.setDateCreation(Date.valueOf(dateCreation)); // Convert to SQL Date
            if (!dateTerminaison.isEmpty()) {
                intervention.setDateTerminaison(Date.valueOf(dateTerminaison));
            }
            intervention.setNomTech(nomTech);
            intervention.setPriority(priority);
            intervention.setBatiment(batiment);
            intervention.setEtage(etage);
            intervention.setNumChambre(numChambre);
            intervention.setEmail(email);
            intervention.setStatut(statut);

            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("L'email est manquant ou invalide.");
            }
            if (priority == null || priority.isEmpty()) {
                request.setAttribute("errorMessage", "Priority is required.");
                request.getRequestDispatcher("/intervention-form.jsp").forward(request, response);
                return;
            }

            // Call DAO to insert the intervention
            int generatedId = interventionDAO.addIntervention(intervention);

            // Redirect or forward based on success
            if (generatedId > 0) {
                response.sendRedirect("interventions?action=list");
            } else {
                request.setAttribute("errorMessage", "Failed to add intervention.");
                request.getRequestDispatcher("/intervention-form.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while adding intervention.");
        }
    }


    private void updateIntervention(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Updating intervention...");
        try {
            int idInterv = Integer.parseInt(request.getParameter("idInterv"));
            String description = request.getParameter("description");
            String dateTerminaison = request.getParameter("dateTerminaison");
            String statut = request.getParameter("statut");
            String priority = request.getParameter("priority");

            System.out.println("Received parameters for update: idInterv=" + idInterv +
                    ", description=" + description +
                    ", dateTerminaison=" + dateTerminaison +
                    ", statut=" + statut +
                    ", priority=" + priority);

            InterventionBean intervention = interventionDAO.getInterventionById(idInterv);
            if (intervention != null) {
                intervention.setDescription(description);
                if (dateTerminaison != null && !dateTerminaison.isEmpty()) {
                    intervention.setDateTerminaison(java.sql.Date.valueOf(dateTerminaison));
                }
                intervention.setStatut(statut);
                intervention.setPriority(priority);

                System.out.println("Updating intervention in database...");
                interventionDAO.updateIntervention(intervention);
                System.out.println("Intervention updated successfully.");
                response.sendRedirect("interventions");
            } else {
                System.out.println("Intervention not found for ID: " + idInterv);
                request.setAttribute("error", "Intervention not found.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/interventions.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            System.err.println("Error while updating intervention: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating intervention.");
        }
    }

    private void deleteIntervention(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Deleting intervention...");
        try {
            int idInterv = Integer.parseInt(request.getParameter("idInterv"));
            System.out.println("Intervention ID to delete: " + idInterv);
            interventionDAO.deleteIntervention(idInterv);
            System.out.println("Intervention deleted successfully.");
            response.sendRedirect("interventions");
        } catch (Exception e) {
            System.err.println("Error while deleting intervention: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting intervention.");
        }
    }

    private void assignTechnicianToIntervention(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Assigning technician to intervention...");
        try {
            int idInterv = Integer.parseInt(request.getParameter("idInterv"));
            String nomTech = request.getParameter("nomTech");

            System.out.println("Assigning technician: " + nomTech + " to intervention ID: " + idInterv);
            boolean success = interventionDAO.assignTechnicianToIntervention(idInterv, nomTech);

            if (success) {
                System.out.println("Technician assigned successfully.");
                request.getSession().setAttribute("message", "Technician assigned successfully.");
            } else {
                System.out.println("Failed to assign technician.");
                request.getSession().setAttribute("message", "Failed to assign technician.");
            }
            response.sendRedirect("interventions");
        } catch (Exception e) {
            System.err.println("Error while assigning technician: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error assigning technician.");
        }
    }
}
