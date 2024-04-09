package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.SkillModel;

public interface SkillRepository {
    public SkillModel createSkill(SkillModel skill);

    public SkillModel getSkill(int skill_id);


}
