package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.EmeSkillModel;

public interface EmeSkillRepository {
    public EmeSkillModel createEmeSkill(EmeSkillModel emeSkill);

    public EmeSkillModel getEmeSkill(int eme_skill_id);


}
