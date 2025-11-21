package project;

/**
 * Classe représentant une région avec son identifiant et son nom.
 */
public class Region {
    private int id;
    private String name;

    // Constructeur par défaut
    public Region() {}

    // Constructeur avec paramètres
    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters et setters pour id et name
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Exemple d'utilisation :
     * Region region = new Region(1, "Provence-Alpes-Côte d'Azur");
     * System.out.println(region.getName()); // Affiche "Provence-Alpes-Côte d'Azur"
     */
}