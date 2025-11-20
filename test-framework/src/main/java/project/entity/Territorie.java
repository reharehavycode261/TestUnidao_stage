package project.entity;

/**
 * Représente une entité de territoire dans le système.
 * Gère les informations spécifiques au territoire.
 */
public class Territorie {

    private String territorieId;
    private String description;

    /**
     * Récupère l'identifiant du territoire.
     * 
     * @return L'identifiant du territoire
     */
    public String getTerritorieId() {
        return territorieId;
    }

    /**
     * Définit l'identifiant du territoire.
     * 
     * @param territorieId L'identifiant du territoire
     */
    public void setTerritorieId(String territorieId) {
        this.territorieId = territorieId;
    }

    /**
     * Récupère la description du territoire.
     * 
     * @return La description du territoire
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description du territoire.
     * 
     * @param description La description du territoire
     */
    public void setDescription(String description) {
        this.description = description;
    }

    // Des méthodes supplémentaires avec documentation peuvent être ajoutées ici
}