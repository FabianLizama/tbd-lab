package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.TaskStateModel;

public interface TaskStateRepository {

    public TaskStateModel createTaskState(TaskStateModel taskState);

    public TaskStateModel getTaskState(int task_state_id);
}
