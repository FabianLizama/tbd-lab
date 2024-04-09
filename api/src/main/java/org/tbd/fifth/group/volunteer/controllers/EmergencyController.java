package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.EmergencyModel;
import org.tbd.fifth.group.volunteer.services.EmergencyService;

public class EmergencyController {

    private final EmergencyService emergencyService;

    public EmergencyController(EmergencyService emergencyService) {
        this.emergencyService = emergencyService;
    }

    @PostMapping("/emergency")
    @ResponseBody
    public EmergencyModel createEmergency(@RequestBody EmergencyModel emergency) {
        return emergencyService.createEmergency(emergency);
    }

    @GetMapping("/emergency/{emergency_id}")
    @ResponseBody
    public EmergencyModel getEmergency(@PathVariable int emergency_id) {
        return emergencyService.getEmergency(emergency_id);
    }
}
