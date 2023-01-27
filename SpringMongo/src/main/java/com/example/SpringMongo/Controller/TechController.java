package com.example.SpringMongo.Controller;

import com.example.SpringMongo.Model.TechThings;
import com.example.SpringMongo.Service.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tech")
public class TechController {
    @Autowired
    TechService service;
    @PostMapping("/")
    public TechThings saveTech(@RequestBody TechThings techThings){
        return service.saveTech(techThings);
    }

    @GetMapping("/")
    public List<TechThings> getAll(){
        return service.getAll();
    }
    @DeleteMapping("/")
    public String deleteTech(@RequestBody TechThings techThings){
        return service.deleteTech(techThings);
    }
}
