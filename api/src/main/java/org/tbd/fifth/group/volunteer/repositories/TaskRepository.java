package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.TaskModel;

import java.util.List;
import java.util.Map;

public interface TaskRepository {
    public TaskModel createTask(TaskModel task, int user_id);

    public TaskModel getTask(int task_id);

    public List<TaskModel> getAllTasks(String token);

    public List<TaskModel> getTasksByEmergencyId(int emergency_id);

    public int countActiveTasksByEmergencyId(int id);

    public int countAllActiveTasks();

    public boolean updateTask(TaskModel task);

    public boolean deleteTask(int task_id);

    public boolean deleteAllTasks();

    public List<Map<String,Object>> getTaskView(String token);

    public List<Map<String,Object>> getTaskViewByEmergencyId(int emergency_id);



}
