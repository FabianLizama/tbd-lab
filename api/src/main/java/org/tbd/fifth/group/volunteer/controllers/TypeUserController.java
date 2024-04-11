package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.TypeUserModel;
import org.tbd.fifth.group.volunteer.services.TypeUserServices;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TypeUserController {

    @Autowired
    private TypeUserServices typeUserServices;

    public TypeUserController(TypeUserServices typeUserServices) {
        this.typeUserServices = typeUserServices;
    }


    @PostMapping("/type_user")
    public String createTypeUser(@RequestBody TypeUserModel typeUser){
        return typeUserServices.createTypeUser(typeUser);
    }

    @GetMapping("/type_user")
    public List<TypeUserModel> getAllTypeUsers(){
        return typeUserServices.getAllTypeUsers();
    }

    @GetMapping("/type_user/{type_user_id}")
    public TypeUserModel getTypeUser(@PathVariable int type_user_id){
        return typeUserServices.findByTypeUserId(type_user_id);
    }

    @DeleteMapping("/type_user")
    public String deleteTypeUser(@RequestBody TypeUserModel typeUser){
        return typeUserServices.deleteTypeUser(typeUser);
    }

    @PutMapping("/type_user")
    public String updateTypeUser(@RequestBody TypeUserModel typeUser){
        return typeUserServices.updateTypeUser(typeUser);
    }

    @GetMapping("/type_user/name/{type_user_name}")
    public TypeUserModel getTypeUserByName(@PathVariable String type_user_name){
        return typeUserServices.findByTypeUserName(type_user_name);
    }


}
