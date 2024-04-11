package org.tbd.fifth.group.volunteer.controllers;


import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.TaskLogModel;
import org.tbd.fifth.group.volunteer.services.TaskLogService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class TaskLogController {

    public final TaskLogService taskLogService;

    public TaskLogController(TaskLogService taskLogService){
        this.taskLogService = taskLogService;
    }

    @PostMapping("/taskLog")
    @ResponseBody
    public TaskLogModel createTaskLog(@RequestBody TaskLogModel taskLog){
        return taskLogService.createTaskLog(taskLog);
    }

    @GetMapping("/taskLog/{task_log_id}")
    @ResponseBody
    public TaskLogModel getTaskLog(@PathVariable int task_log_id){
        return taskLogService.getTaskLog(task_log_id);
    }
}
