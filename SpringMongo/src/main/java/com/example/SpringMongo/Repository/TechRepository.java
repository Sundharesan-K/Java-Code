package com.example.SpringMongo.Repository;

import com.example.SpringMongo.Model.TechThings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TechRepository {
    @Autowired
    MongoTemplate template;

    public TechThings saveTech(TechThings techThings) {
        return template.save (techThings);
    }


    public List<TechThings> getAll() {
        return template.findAll (TechThings.class);
    }

    public String deleteTech(TechThings techThings) {
        template.remove (techThings);
        return "Delete Successfully ";
    }
}
