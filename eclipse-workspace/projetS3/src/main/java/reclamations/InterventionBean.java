package reclamations;

import java.util.Date;

public class InterventionBean {
	private String priority;
    private int idInterv;
    private String description;
    private Date dateCreation;
    private Date dateTerminaison;
    private String nomTech;
    private String statut;
    private String batiment;
    private int etage;
    private int numChambre;
    private String email;
    private int evaluation;
    private String commentaire;


    // Default constructor
    public InterventionBean() {}

    // Constructor with parameters
    public InterventionBean(String priority, int idInterv, String description, Date dateCreation, Date dateTerminaison, String nomTech, String statut, String batiment, int etage, int numChambre, String email, int evaluation, String commentaire) {
        this.priority = priority;
    	this.idInterv = idInterv;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateTerminaison = dateTerminaison;
        this.nomTech = nomTech;
        this.statut = statut;
        this.batiment = batiment;
        this.etage = etage;
        this.numChambre = numChambre;
        this.email = email;
        this.evaluation = evaluation;
        this.commentaire = commentaire;
    }

    // Getters and Setters
    
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
    public int getIdInterv() {
        return idInterv;
    }

    public void setIdInterv(int idInterv) {
        this.idInterv = idInterv;
    }
    
    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateTerminaison() {
        return dateTerminaison;
    }

    public void setDateTerminaison(Date dateTerminaison) {
        this.dateTerminaison = dateTerminaison;
    }

    public String getNomTech() {
        return nomTech;
    }

    public void setNomTech(String nomTech) {
        this.nomTech = nomTech;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getBatiment() {
        return batiment;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public int getNumChambre() {
        return numChambre;
    }

    public void setNumChambre(int numChambre) {
        this.numChambre = numChambre;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    // toString method for displaying
    @Override
    public String toString() {
        return "InterventionBean{" +
                "idInterv=" + idInterv +
                ", description='" + description + '\'' +
                ", dateCreation=" + dateCreation +
                ", dateTerminaison=" + dateTerminaison +
                ", nomTech=" + nomTech +
                ", statut='" + statut + '\'' +
                ", batiment='" + batiment + '\'' +
                ", etage=" + etage +
                ", numChambre=" + numChambre +
                ", email=" + email +
                '}';
    }
}
