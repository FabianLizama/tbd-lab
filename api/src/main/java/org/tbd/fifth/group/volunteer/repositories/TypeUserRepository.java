package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.TypeUserModel;

import java.util.List;

public interface TypeUserRepository {

//metodos CRUD

    public List<TypeUserModel> getAllTypeUsers();
    public String createTypeUser(TypeUserModel typeUserModel);
    public TypeUserModel findByTypeUserName(String typeUserName);
    //metodo eliminar
    public String deleteTypeUser(TypeUserModel typeUserModel);
    //metodo actualizar
    public String updateTypeUser(TypeUserModel typeUserModel);
    //metodo buscar por id
    public TypeUserModel findByTypeUserId(int typeUserId);



}
