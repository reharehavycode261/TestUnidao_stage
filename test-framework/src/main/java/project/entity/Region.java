package project.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import mg.uniDao.annotation.Collection;
import mg.uniDao.annotation.Field;

@Collection
public class Region {
    
    @Field(name = "region_id", isPrimaryKey = true)
    private String regionId;

    @NotEmpty(message = "Description cannot be empty")
    @Size(max = 255, message = "Description must be less than 255 characters")
    @Field(name = "region_description")
    private String regionDescription;

    public Region() {}

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