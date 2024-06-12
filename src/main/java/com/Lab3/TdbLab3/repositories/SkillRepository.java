package com.Lab3.TdbLab3.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.Lab3.TdbLab3.models.Skill; // Asume que tienes una clase Skill en este paquete
import java.util.Optional;

public interface SkillRepository extends MongoRepository<Skill, String> {
    // Método para buscar habilidades por código
    Optional<Skill> findByCode(String code);
}
