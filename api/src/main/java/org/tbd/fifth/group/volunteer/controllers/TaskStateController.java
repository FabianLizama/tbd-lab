package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.TaskStateModel;
import org.tbd.fifth.group.volunteer.services.TaskStateService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TaskStateController {

    private final TaskStateService taskStateService;

    public TaskStateController(TaskStateService taskStateService) {
        this.taskStateService = taskStateService;
    }

    @PostMapping("/task_state")
    public TaskStateModel createTaskState(@RequestBody TaskStateModel taskState){
        return taskStateService.createTaskState(taskState);
    }

    @GetMapping("/task_state/{task_state_id}")
    public TaskStateModel getTaskState(@PathVariable int task_state_id){
        return taskStateService.getTaskState(task_state_id);
    }

    @PutMapping("/task_state")
    public TaskStateModel updateTaskState(@RequestBody TaskStateModel taskState){
        return taskStateService.UpdateTaskState(taskState);
    }
}
