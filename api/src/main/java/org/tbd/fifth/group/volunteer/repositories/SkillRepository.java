package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.SkillModel;

import java.util.List;

public interface SkillRepository {
    public SkillModel createSkill(SkillModel skill);

    public SkillModel getSkill(int skill_id);

    public List<SkillModel> getSkills();


}
