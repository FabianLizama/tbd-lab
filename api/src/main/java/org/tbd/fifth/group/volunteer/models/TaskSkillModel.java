package org.tbd.fifth.group.volunteer.models;

public class TaskSkillModel {
    private int task_skill_id;
    private int eme_skill_id;

    public TaskSkillModel(int task_skill_id, int eme_skill_id) {
        this.task_skill_id = task_skill_id;
        this.eme_skill_id = eme_skill_id;
    }

    public int getTask_skill_id() {
        return task_skill_id;
    }

    public void setTask_skill_id(int task_skill_id) {
        this.task_skill_id = task_skill_id;
    }

    public int getEme_skill_id() {
        return eme_skill_id;
    }

    public void setEme_skill_id(int eme_skill_id) {
        this.eme_skill_id = eme_skill_id;
    }

    // Getters y setters...
}
