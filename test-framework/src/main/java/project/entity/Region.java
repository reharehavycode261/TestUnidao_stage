package project.entity;

import mg.uniDao.annotation.Field;
import mg.uniDao.annotation.AutoSequence;
import mg.uniDao.core.sql.GenericSqlDao;
import mg.uniDao.annotation.Collection;
import com.google.gson.Gson;
import java.util.UUID;
import java.util.List;

@Collection
public class Region extends GenericSqlDao {
    @Field(name = "region_id", isPrimaryKey = true)
    @AutoSequence(name = "region_id_seq")
    private Integer regionId;
    
    @Field(name = "region_name")
    private String regionName;
    
    @Field(name = "metadata", typeHandler = "json")
    private Object metadata; // JSON data
    
    @Field(name = "uuid")
    private UUID uuid; // UUID data
    
    @Field(name = "sub_regions", typeHandler = "array")
    private List<String> subRegions; // Array of strings

    // Getters and setters go here
}