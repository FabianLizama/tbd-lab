package com.Lab3.TdbLab3.services;


import com.Lab3.TdbLab3.models.Skill;
import com.Lab3.TdbLab3.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin
@RestController
public class SkillService {

    @Autowired
    SkillRepository skillRepository;

    @GetMapping("/skills/{code}")
    public Skill getSkillByCode(@PathVariable String code) {
        Optional<Skill> skill = skillRepository.findByCode(code);
        return skill.orElseThrow(() -> new RuntimeException("Skill not found with code: " + code));
    }
}
