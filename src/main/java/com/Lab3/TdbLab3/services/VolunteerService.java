package com.Lab3.TdbLab3.services;

import com.Lab3.TdbLab3.models.Volunteer;
import com.Lab3.TdbLab3.repositories.VolunteerRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class VolunteerService {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/volunteers")
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    @GetMapping("/volunteers/name/{name}")
    public List<Volunteer> getVolunteerByName(@PathVariable("name") String name) {
        return volunteerRepository.findByFullname(name);
    }

    @GetMapping("/volunteers/skills/{rut}")
    public List<Object> getVolunteerSkills(@PathVariable("rut") String rut) {
        return volunteerRepository.findVolunteerSkillsByRut(rut);
    }

}
