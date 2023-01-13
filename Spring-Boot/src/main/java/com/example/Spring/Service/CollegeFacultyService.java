package com.example.Spring.Service;

import com.example.Spring.CollegeFaculty;
import com.example.Spring.Repository.CollegeFacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class CollegeFacultyService {
    @Autowired
    private CollegeFacultyRepository collegeFacultyRepository;

    public List<CollegeFaculty> getAllCollegeFaculty(){
        return collegeFacultyRepository.getAllFaculty ();
    }
    public CollegeFaculty getOneFaculty(String id){
        return collegeFacultyRepository.getOneFaculty (id);
    }
    public void create(CollegeFaculty collegeFaculty){
        collegeFacultyRepository.create (collegeFaculty);
    }
    public void delete(String id){
        collegeFacultyRepository.delete (id);
    }
    public void update(CollegeFaculty collegeFaculty){
        collegeFacultyRepository.update (collegeFaculty);
    }
    public List<CollegeFaculty> getCollegeFacultyRequest(String pageNumber,String pageSize){
        return collegeFacultyRepository.getAllCollegeFacultyRequest(pageNumber,pageSize);

    }

}
