package project.entity;

// TODO: Optimiser l'utilisation des annotations présentes sur les champs

import mg.uniDao.annotation.Field;
import mg.uniDao.annotation.AutoSequence;
import mg.uniDao.core.sql.GenericSqlDao;
import mg.uniDao.annotation.Collection;

@Collection
public class Region extends GenericSqlDao {
    @Field(name = "region_id", isPrimaryKey = true)
    @AutoSequence(name = "region_id")
    private Integer regionId;
    
    @Field(name = "region_description")
    private String regionDescription;
    
    @Field(name = "is_deleted")
    private boolean isDeleted;

    // TODO: Revoir l'implémentation pour gérer le champ isDeleted lors des opérations

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Region{" +
               "regionId=" + regionId +
               ", regionDescription='" + regionDescription + '\'' +
               '}';
    }
}