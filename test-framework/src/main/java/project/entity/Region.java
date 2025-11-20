package project.entity;

import mg.uniDao.annotation.AutoSequence;
import mg.uniDao.annotation.Collection;
import mg.uniDao.annotation.Field;
import mg.uniDao.core.sql.GenericSqlDao;

/**
 * Représente une entité de région dans le système.
 * Gère les informations de la région, y compris son
 * identifiant unique et sa description.
 */
@Collection
public class Region extends GenericSqlDao {
    @AutoSequence(name = "student", prefix = "ETU", length = 8)
    @Field(name = "region_id", isPrimaryKey = true)
    private String regionId;

    @Field(name = "region_description")
    private String regionDescription;

    /**
     * Constructeur par défaut.
     */
    public Region() {
    }

    /**
     * Constructeur avec paramètres pour initialiser une région avec un identifiant.
     * 
     * @param regionId L'identifiant de la région
     */
    public Region(String regionId) {
        this.regionId = regionId;
    }

    // Assurez-vous d'ajouter des getters et setters avec documentation si existent

    // Des méthodes supplémentaires avec documentation peuvent être ajoutées ici
}