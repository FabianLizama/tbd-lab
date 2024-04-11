package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.VolSkillModel;
import org.tbd.fifth.group.volunteer.services.VolSkillServices;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class VolSkillController {

    private final VolSkillServices volSkillServices;

    public VolSkillController(VolSkillServices volSkillServices) {
        this.volSkillServices = volSkillServices;
    }

    @PostMapping("/volSkill")
    @ResponseBody
    public VolSkillModel createVolSkill(@RequestBody VolSkillModel volSkill) {
        return volSkillServices.createVolSkill(volSkill);
    }

    @GetMapping("/volSkill/{vol_skill_id}")
    @ResponseBody
    public VolSkillModel getVolSkill(@PathVariable int vol_skill_id) {
        return volSkillServices.getVolSkill(vol_skill_id);
    }

}
