package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.CoordinatorModel;
import org.tbd.fifth.group.volunteer.services.CoordinatorService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CoordinatorController {

    private final CoordinatorService coordinatorService;

    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    @PostMapping("/coordinator")
    public CoordinatorModel createCoordinator(@RequestBody CoordinatorModel coordinator) {
        return coordinatorService.createCoordinator(coordinator);
    }

    @GetMapping("/coordinator/{coordinator_id}")
    public CoordinatorModel getCoordinator(@PathVariable int coordinator_id) {
        return coordinatorService.getCoordinator(coordinator_id);
    }

    @GetMapping("/coordinator/user/{user_id}")
    public int getId_Coordinator(@PathVariable int user_id) {
        return coordinatorService.getId_Coordinator(user_id);
    }



}
