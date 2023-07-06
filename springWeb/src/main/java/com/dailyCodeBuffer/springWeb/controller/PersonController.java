package com.dailyCodeBuffer.springWeb.controller;

import com.dailyCodeBuffer.springWeb.entity.Person;
import com.dailyCodeBuffer.springWeb.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;
    @PostMapping("/create")
    public String save(@RequestBody Person person){
        return personService.save(person);
    }
    @GetMapping
    public Person getByFirstName(@RequestParam(value = "firstName") String firstName){
        return personService.getByFirstName(firstName);
    }

    @GetMapping("/getId")
    public Person getByPersonId(@RequestParam(value = "personId") String personId){
        return personService.getByPersonId(personId);
    }
    @GetMapping("/getAge")
    public List<Person> getPersonByAge(@RequestParam Integer minAge,@RequestParam Integer maxAge){
        return personService.getPersonByAge(minAge,maxAge);
    }

    @GetMapping("/search")
    public Page<Person> personPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
            ){
        Pageable pageable = PageRequest.of(page,size);
        return personService.search(name,minAge,maxAge,city,pageable);
    }
    @GetMapping("/oldestPerson")
    public List<Document> getByOldestPerson(){
        return personService.getByOldestPerson();
    }
    @GetMapping("/getPopulation")
    public List<Document> getPopulationByCity(){
        return personService.getPopulationByCity();
    }
}
