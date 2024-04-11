package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.TaskModel;

import java.util.List;

public interface TaskRepository {
    public TaskModel createTask(TaskModel task);

    public TaskModel getTask(int task_id);

    public List<TaskModel> getAllTasks(String token);

    public List<TaskModel> getTasksByEmergencyId(int emergency_id);

    public int countActiveTasksByEmergencyId(int id);

    public int countAllActiveTasks();

    public boolean updateTask(TaskModel task);

    public boolean deleteTask(int task_id);

    public boolean deleteAllTasks();



}
