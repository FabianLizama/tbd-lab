package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.TaskModel;
import org.tbd.fifth.group.volunteer.services.TaskService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task")
    @ResponseBody
    public TaskModel createTask(@RequestBody TaskModel task) {
        return taskService.createTask(task);
    }

    @GetMapping("/task/{task_id}")
    @ResponseBody
    public TaskModel getTask(@PathVariable int task_id) {
        return taskService.getTask(task_id);
    }

    @GetMapping("/task/view")
    @ResponseBody
    public List<Map<String,Object>> getTaskView(@RequestParam String token) {
        return taskService.getTaskView(token);
    }
}
