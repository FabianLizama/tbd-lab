package org.tbd.fifth.group.volunteer.models;

public class EmeSkillModel {
    private int eme_skill_id;
    private int emergency_id;
    private int skill_id;

    public EmeSkillModel(int eme_skill_id, int emergency_id, int skill_id) {
        this.eme_skill_id = eme_skill_id;
        this.emergency_id = emergency_id;
        this.skill_id = skill_id;
    }

    public int getEme_skill_id() {
        return eme_skill_id;
    }

    public void setEme_skill_id(int eme_skill_id) {
        this.eme_skill_id = eme_skill_id;
    }

    public int getEmergency_id() {
        return emergency_id;
    }

    public void setEmergency_id(int emergency_id) {
        this.emergency_id = emergency_id;
    }

    public int getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(int skill_id) {
        this.skill_id = skill_id;
    }
}
