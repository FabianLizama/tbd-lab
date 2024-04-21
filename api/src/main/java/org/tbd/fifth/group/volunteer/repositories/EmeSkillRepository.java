package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.EmeSkillModel;

import java.util.List;
import java.util.Map;

public interface EmeSkillRepository {
    public EmeSkillModel createEmeSkill(EmeSkillModel emeSkill);

    public EmeSkillModel getEmeSkill(int eme_skill_id);

    public List<Map<String, Object>> getEmeSkillsByEmergency(int emergency_id);


}
