package project.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import mg.uniDao.annotation.Collection;
import mg.uniDao.annotation.Field;

@Collection
public class Territorie {
    
    @Field(name = "territorie_id", isPrimaryKey = true)
    private String territorieId;

    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 255, message = "Name must be less than 255 characters")
    @Field(name = "territorie_name")
    private String territorieName;

    public Territorie() {}

    public Territorie(String territorieId, String territorieName) {
        this.territorieId = territorieId;
        this.territorieName = territorieName;
    }

    public String getTerritorieId() {
        return territorieId;
    }

    public void setTerritorieId(String territorieId) {
        this.territorieId = territorieId;
    }

    public String getTerritorieName() {
        return territorieName;
    }

    public void setTerritorieName(String territorieName) {
        this.territorieName = territorieName;
    }
}