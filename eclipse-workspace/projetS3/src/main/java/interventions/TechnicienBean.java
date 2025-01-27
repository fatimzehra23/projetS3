package interventions;

public class TechnicienBean {
    private int idTech;
    private String nomTech;
    private String specialite;
    private String disponibilite; // true for available, false for not available
    private String email;
    private String telephone;

    // Constructor with no arguments
    public TechnicienBean() {}

    // Constructor with all arguments
    public TechnicienBean(int idTech, String nomTech, String specialite, String disponibilite, String email, String telephone) {
        this.idTech = idTech;
        this.nomTech = nomTech;
        this.specialite = specialite;
        this.disponibilite = disponibilite;
        this.email = email;
        this.telephone = telephone;
    }

    // Getters and Setters
    public int getIdTech() {
        return idTech;
    }

    public void setIdTech(int idTech) {
        this.idTech = idTech;
    }

    public String getNomTech() {
        return nomTech;
    }

    public void setNomTech(String nomTech) {
        this.nomTech = nomTech;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "TechnicienBean{" +
                "idTech=" + idTech +
                ", nomTech='" + nomTech + '\'' +
                ", specialite='" + specialite + '\'' +
                ", disponibilite=" + disponibilite +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}

