package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.TaskModel;

public interface TaskRepository {
    public TaskModel createTask(TaskModel task);

    public TaskModel getTask(int task_id);

}
