package com.dailyCodeBuffer.springWeb.repository;

import com.dailyCodeBuffer.springWeb.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person,String> {

    Person findByFirstName(String firstName);
    @Query(value = "{'age':{$gte : ?0, $lte : ?1}}",fields = "{'hobbies': 0}")
    List<Person> findPersonByAgeBetween(Integer min,Integer max);
}
