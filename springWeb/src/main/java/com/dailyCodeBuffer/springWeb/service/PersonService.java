package com.dailyCodeBuffer.springWeb.service;

import com.dailyCodeBuffer.springWeb.entity.Person;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    String save(Person person);

    Person getByFirstName(String firstName);

    Person getByPersonId(String personId);

    List<Person> getPersonByAge(Integer minAge, Integer maxAge);

    Page<Person> search(String name, Integer minAge, Integer maxAge, String city, Pageable pageable);

    List<Document> getByOldestPerson();

    List<Document> getPopulationByCity();
}
