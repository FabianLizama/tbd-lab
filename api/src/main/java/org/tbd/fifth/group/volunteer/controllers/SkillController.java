package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.SkillModel;
import org.tbd.fifth.group.volunteer.services.SkillService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping("/skill")
    @ResponseBody
    public SkillModel createSkill(@RequestBody SkillModel skill) {
        return skillService.createSkill(skill);
    }

    @GetMapping("/skill/{skill_id}")
    @ResponseBody
    public SkillModel getSkill(@PathVariable int skill_id) {
        return skillService.getSkill(skill_id);
    }
}
