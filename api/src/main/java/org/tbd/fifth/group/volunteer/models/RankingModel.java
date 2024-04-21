package org.tbd.fifth.group.volunteer.models;

public class RankingModel {
    private int ranking_id;

    private int volunteer_id;

    private int task_id;

    private double grade;

    private boolean accepted;


    public RankingModel(int ranking_id, int volunteer_id, int task_id, double grade, boolean accepted) {
        this.ranking_id = ranking_id;
        this.volunteer_id = volunteer_id;
        this.task_id = task_id;
        this.grade = grade;
        this.accepted = accepted;
    }

    public int getRanking_id() {
        return ranking_id;
    }

    public void setRanking_id(int ranking_id) {
        this.ranking_id = ranking_id;
    }

    public int getVolunteer_id() {
        return volunteer_id;
    }

    public void setVolunteer_id(int volunteer_id) {
        this.volunteer_id = volunteer_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
