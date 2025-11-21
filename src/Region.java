package project;

// TODO: Vérifier les dépendances inutilisées et optimiser les imports

import java.util.List;

public class Region {
    private Integer id;
    private String name;
    
    // TODO: Implémentez des validations pour les champs de la région

    public Region(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // TODO: Ajouter la gestion d'erreurs si l'ID est null

    @Override
    public String toString() {
        return "Region{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}