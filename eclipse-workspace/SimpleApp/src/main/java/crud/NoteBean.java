package crud;

public class NoteBean {
	private int id;
    private String title; // Correction du nom de la variable
    private String content;

    // Constructeur par défaut
    public NoteBean() {}
    
    // Constructeur avec paramètres
    public NoteBean(String title, String content) {
        this.title = title;
        this.content = content;
    }
    // Getter pour title
    public int getId() {
        return id;
    }

    // Setter pour title
    public void setId(int id) {
        this.id = id;
    }

    // Getter pour title
    public String getTitle() {
        return title;
    }

    // Setter pour title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter pour content
    public String getContent() {
        return content;
    }

    // Setter pour content
    public void setContent(String content) {
        this.content = content;
    }

    // Méthode toString pour afficher les informations de la note
    @Override
    public String toString() {
        return "NoteBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
