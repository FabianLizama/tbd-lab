package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.services.EmeSkillService;
import org.tbd.fifth.group.volunteer.models.EmeSkillModel;

@RestController
@CrossOrigin
public class EmeSkillController {

    public final EmeSkillService emeSkillService;

    public EmeSkillController(EmeSkillService emeSkillService) {
        this.emeSkillService = emeSkillService;
    }

    @PostMapping("/emeSkill")
    @ResponseBody
    public EmeSkillModel createEmeSkill(@RequestBody EmeSkillModel emeSkill) {
        return emeSkillService.createEmeSkill(emeSkill);
    }

    @GetMapping("/emeSkill/{eme_skill_id}")
    @ResponseBody
    public EmeSkillModel getEmeSkill(@PathVariable int eme_skill_id) {
        return emeSkillService.getEmeSkill(eme_skill_id);
    }


}
