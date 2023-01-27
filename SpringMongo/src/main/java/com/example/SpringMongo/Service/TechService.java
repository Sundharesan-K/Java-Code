package com.example.SpringMongo.Service;

import com.example.SpringMongo.Model.TechThings;
import com.example.SpringMongo.Repository.TechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechService {
    @Autowired
    TechRepository repository;

    public TechThings saveTech(TechThings techThings) {
        return repository.saveTech(techThings);
    }


    public List<TechThings> getAll() {
        return repository.getAll();
    }

    public String deleteTech(TechThings techThings) {
        return repository.deleteTech(techThings);
    }
}
