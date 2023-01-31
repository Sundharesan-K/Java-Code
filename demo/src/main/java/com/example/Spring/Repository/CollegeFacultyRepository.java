package com.example.Spring.Repository;
import com.example.Spring.CollegeFaculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class CollegeFacultyRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<CollegeFaculty> getAllFaculty(){
        return mongoTemplate.findAll (CollegeFaculty.class);
    }
    public CollegeFaculty getOneFaculty(String id){
        return mongoTemplate.findOne (Query.query (Criteria.where ("facId").is (id)),CollegeFaculty.class);
    }
    public void create(CollegeFaculty collegeFaculty){
        mongoTemplate.save (collegeFaculty);
    }
    public void delete(String id){
        mongoTemplate.remove (Query.query (Criteria.where ("facId").is (id)),CollegeFaculty.class);
    }
    public void update(CollegeFaculty ColFac) {
        Query query = new Query().addCriteria(Criteria.where("facId").is(collegeFaculty.getCollegeFaculty(id)));
        Update update = new Update();
        update.set("name", ColFac.getName());
        update.set("description", ColFac.getPhoneNumber());
        mongoTemplate.findAndModify(query, update, CollegeFaculty.class);
    }
    public List<CollegeFaculty> getAllEmployeePaginated(String pageNumber, String pageSize) {
        Query query = new Query().skip(Integer.parseInt(pageNumber) * Integer.parseInt(pageSize)).limit(Integer.parseInt(pageSize));
        return mongoTemplate.find(query, CollegeFaculty.class);
    }

}
