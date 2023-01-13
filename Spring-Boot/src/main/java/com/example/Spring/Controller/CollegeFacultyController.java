package com.example.Spring.Controller;

import com.example.Spring.CollegeFaculty;
import com.example.Spring.service.CollegeFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CollegeFaculty")
public class CollegeFacultyController {
   @Autowired
    CollegeFacultyService facService;
    @GetMapping
    public List<CollegeFaculty> getCollegeFaculty(){return facService.getAllCollegeFaculty();
    }
    @GetMapping("/{id}")
    public CollegeFaculty getoneCollegeFacultyById(@PathVariable String id){
        return facService.getOneCollegeFacculty(id);
    }
    @GetMapping
    public List<CollegeFaculty> getCollegeFacultyRequest(@RequestParam String pageNumber, @RequestParam String pageSize){
        return facService.getAllCollegeFaculty(pageNumber,pageSize);
    }
    @PostMapping
    public String create(@RequestBody CollegeFaculty newCollegeFaculty){
        facService.create(newCollegeFaculty);
        return "user created";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        facService.delete(id);
        return "User delete";
    }

    public String update(@RequestBody CollegeFaculty collegeFaculty){
        facService.update(collegeFaculty);
        return "user updated";
    }


}
