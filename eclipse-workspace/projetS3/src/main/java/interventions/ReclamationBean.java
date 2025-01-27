package interventions;

public class ReclamationBean {
    private int idRecl;
    private String sujet;
    private String description;
    private java.util.Date dateCreation;
    private String batiment;
    private int etage;
    private int numChambre;
    private String email;

    // Default constructor
    public ReclamationBean() {
    }

    // Constructor with parameters
    public ReclamationBean(int idRecl, String sujet, String description, java.util.Date dateCreation, String batiment, int etage, int numChambre, String email) {
        this.idRecl = idRecl;
        this.sujet = sujet;
        this.description = description;
        this.dateCreation = dateCreation;
        this.email = email;
        this.batiment = batiment;
        this.etage = etage;
        this.numChambre = numChambre;
    }

    // Getter and Setter methods
    public int getIdRecl() {
        return idRecl;
    }

    public void setIdRecl(int idRecl) {
        this.idRecl = idRecl;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(java.util.Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    // toString method for easy display
    @Override
    public String toString() {
        return "ReclamationBean{" +
                "idReclamation=" + idRecl +
                ", sujet='" + sujet + '\'' +
                ", description='" + description + '\'' +
                ", dateCreation=" + dateCreation +
                ", email=" + email +
                ", batiment=" + batiment +
                ", etage=" + etage +
                ", numChambre=" + numChambre +
                '}';
    }
}
