package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.InstitutionModel;

import java.util.List;

public interface InstitutionRepository {
    public InstitutionModel createInstitution(InstitutionModel institution);

    public InstitutionModel getInstitution(int institution_id);

    public List<InstitutionModel> getAllInstitutions();


}
