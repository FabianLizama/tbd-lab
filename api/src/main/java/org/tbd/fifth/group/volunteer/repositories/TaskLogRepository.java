package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.TaskLogModel;

public interface TaskLogRepository {
    public TaskLogModel createTaskLog(TaskLogModel taskLog);

    public TaskLogModel getTaskLog(int task_log_id);

}
