package com.Lab3.TdbLab3.repositories;

import com.Lab3.TdbLab3.models.Volunteer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VolunteerRepository extends MongoRepository<Volunteer, ObjectId> {

    // Buscar voluntarios por nombre completo
    List<Volunteer> findByFullname(String fullname);

    // Ejemplo de una agregación compleja: obtener detalles de habilidades de los voluntarios
    // Ejemplo de una agregación compleja: obtener detalles de habilidades de los voluntarios basado en el RUT
    // Ejemplo de una agregación compleja: obtener detalles de habilidades de los voluntarios basado en el RUT
    @Aggregation(pipeline = {
            "{ $match: { 'rut': ?0 } }",
            "{ $lookup: { from: 'Skill', localField: 'abilities._id', foreignField: '_id', as: 'skills' } }",
            "{ $unwind: { path: '$skills', preserveNullAndEmptyArrays: true } }",
            "{ $project: { 'skills.code': 1, 'skills.name': 1, 'skills.items': 1, 'skills.description': 1 } }"
    })
    List<Object> findVolunteerSkillsByRut(String rut);
}
