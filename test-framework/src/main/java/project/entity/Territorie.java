package project.entity;

import mg.uniDao.annotation.Field;
import mg.uniDao.annotation.AutoSequence;
import mg.uniDao.core.sql.GenericSqlDao;
import mg.uniDao.annotation.Collection;

@Collection
public class Territorie extends GenericSqlDao {
    @Field(name = "territorie_id", isPrimaryKey = true)
    @AutoSequence(name = "territorie_id")
    private Integer territorieId;
    
    @Field(name = "territorie_description")
    private String territorieDescription;
    
    @Field(name = "is_deleted")
    private boolean isDeleted = false; // Ajout de la suppression logique

    public Integer getTerritorieId() {
        return territorieId;
    }
    
    public void setTerritorieId(Integer territorieId) {
        this.territorieId = territorieId;
    }
    
    public String getTerritorieDescription() {
        return territorieDescription;
    }
    
    public void setTerritorieDescription(String territorieDescription) {
        this.territorieDescription = territorieDescription;
    }
    
    public boolean isDeleted() {
        return isDeleted;
    }
    
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    // Logique pour marquer comme supprimé
    public void softDelete() {
        this.isDeleted = true;
    }
}