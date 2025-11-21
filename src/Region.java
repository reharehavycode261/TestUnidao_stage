import mg.uniDao.annotation.AutoSequence;
import mg.uniDao.annotation.Collection;
import mg.uniDao.annotation.Field;
import mg.uniDao.core.sql.GenericSqlDao;

/**
 * Représente une région avec un identifiant unique et une description.
 * 
 * Exemple d'utilisation:
 * <pre>
 * {@code
 * // Créer une nouvelle instance de Region
 * Region region = new Region();
 * 
 * // Initialiser une région avec des paramètres
 * Region regionWithDetails = new Region("R001", "North Region");
 * 
 * // Afficher les informations de la région
 * System.out.println(regionWithDetails.getRegionId());
 * System.out.println(regionWithDetails.getRegionDescription());
 * }
 * </pre>
 */
@Collection
public class Region extends GenericSqlDao {
    @AutoSequence(name = "student", prefix = "ETU", length = 8)
    @Field(name = "region_id", isPrimaryKey = true)
    private String regionId;

    @Field(name = "region_description")
    private String regionDescription;

    public Region() {
    }

    public Region(String regionId, String regionDescription) {
        this.regionId = regionId;
        this.regionDescription = regionDescription;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }
}