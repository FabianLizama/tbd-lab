package org.tbd.fifth.group.volunteer.models;

public class VolSkillModel {
    private int vol_skill_id;
    private int volunteer_id;
    private int skill_id;

    public VolSkillModel(int vol_skill_id, int volunteer_id, int skill_id) {
        this.vol_skill_id = vol_skill_id;
        this.volunteer_id = volunteer_id;
        this.skill_id = skill_id;
    }

    public int getVol_skill_id() {
        return vol_skill_id;
    }

    public void setVol_skill_id(int vol_skill_id) {
        this.vol_skill_id = vol_skill_id;
    }

    public int getVolunteer_id() {
        return volunteer_id;
    }

    public void setVolunteer_id(int volunteer_id) {
        this.volunteer_id = volunteer_id;
    }

    public int getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(int skill_id) {
        this.skill_id = skill_id;
    }
}
