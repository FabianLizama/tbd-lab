package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.EmergencyModel;
import org.tbd.fifth.group.volunteer.services.EmergencyService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EmergencyController {

    private final EmergencyService emergencyService;

    public EmergencyController(EmergencyService emergencyService) {
        this.emergencyService = emergencyService;
    }

    @PostMapping("/emergency/create")
    @ResponseBody
    public EmergencyModel createEmergency(@RequestBody EmergencyModel emergency, @RequestParam String token) {
        return emergencyService.createEmergency(emergency,token);
    }

    @GetMapping("/emergency/{emergency_id}")
    @ResponseBody
    public EmergencyModel getEmergency(@PathVariable int emergency_id) {
        return emergencyService.getEmergency(emergency_id);
    }

    @GetMapping("/emergency")
    @ResponseBody
    public List<EmergencyModel> getAllEmergencies(@RequestParam String token) {
        return emergencyService.getAllEmergencies(token);
    }

    @GetMapping("/emergency/actives")
    @ResponseBody
    public List<EmergencyModel> getEmergenciesActives(@RequestParam String token) {
        return emergencyService.getEmergenciesActives(token);
    }

    @GetMapping("/emergency/view")
    @ResponseBody
    public List<EmergencyModel> getEmergenciesView(@RequestParam String token) {
        return emergencyService.getEmergenciesView(token);
    }
}
