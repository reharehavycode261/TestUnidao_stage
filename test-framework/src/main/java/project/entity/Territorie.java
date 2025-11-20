package project.entity;

import mg.uniDao.annotation.Field;

public class Territorie {
    @Field(name = "territorie_id", isPrimaryKey = true)
    private String territorieId;

    @Field(name = "territorie_description")
    private String territorieDescription;

    public Territorie() {
    }

    public Territorie(String territorieId, String territorieDescription) {
        this.territorieId = territorieId;
        this.territorieDescription = territorieDescription;
    }

    public String getTerritorieId() {
        return territorieId;
    }

    public void setTerritorieId(String territorieId) {
        this.territorieId = territorieId;
    }

    public String getTerritorieDescription() {
        return territorieDescription;
    }

    public void setTerritorieDescription(String territorieDescription) {
        this.territorieDescription = territorieDescription;
    }

    @Override
    public String toString() {
        return "Territorie{" +
                "territorieId='" + territorieId + '\'' +
                ", territorieDescription='" + territorieDescription + '\'' +
                '}';
    }
}