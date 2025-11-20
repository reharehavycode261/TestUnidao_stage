package project.entity;

import mg.uniDao.annotation.Field;
import mg.uniDao.annotation.AutoSequence;
import mg.uniDao.core.sql.GenericSqlDao;
import mg.uniDao.annotation.Collection;
import com.google.gson.Gson;
import java.util.UUID;
import java.util.List;

@Collection
public class Territorie extends GenericSqlDao {
    @Field(name = "territorie_id", isPrimaryKey = true)
    @AutoSequence(name = "territorie_id_seq")
    private Integer territorieId;
    
    @Field(name = "territorie_description")
    private String territorieDescription;
    
    @Field(name = "attributes", typeHandler = "json")
    private Object attributes; // JSON data
    
    @Field(name = "uuid")
    private UUID uuid; // UUID data
    
    @Field(name = "neighboringRegions", typeHandler = "array")
    private List<String> neighboringRegions; // Array of strings

    // Getters and setters go here
}