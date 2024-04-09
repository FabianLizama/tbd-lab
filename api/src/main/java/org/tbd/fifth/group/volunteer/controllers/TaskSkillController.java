package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.TaskSkillModel;
import org.tbd.fifth.group.volunteer.repositories.TaskSkillRepository;

@RestController
@CrossOrigin
public class TaskSkillController {

    private final TaskSkillRepository taskSkillRepository;

    public TaskSkillController(TaskSkillRepository taskSkillRepository) {
        this.taskSkillRepository = taskSkillRepository;
    }

    @PostMapping("/taskSkill")
    public TaskSkillModel createTaskSkill(@RequestBody TaskSkillModel taskSkill) {
        return taskSkillRepository.createTaskSkill(taskSkill);
    }

    @GetMapping("/taskSkill/{task_skill_id}")
    public TaskSkillModel getTaskSkill(@PathVariable int task_skill_id) {
        return taskSkillRepository.getTaskSkill(task_skill_id);
    }
}
