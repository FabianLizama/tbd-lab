package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.TaskSkillModel;

public interface TaskSkillRepository {

    public TaskSkillModel createTaskSkill(TaskSkillModel taskSkill);

    public TaskSkillModel getTaskSkill(int task_skill_id);
}
