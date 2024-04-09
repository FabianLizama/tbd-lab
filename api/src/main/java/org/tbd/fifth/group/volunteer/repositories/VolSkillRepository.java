package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.VolSkillModel;

public interface VolSkillRepository {

    public VolSkillModel createVolSkill(VolSkillModel volSkill);

    public VolSkillModel getVolSkill(int vol_skill_id);
}
