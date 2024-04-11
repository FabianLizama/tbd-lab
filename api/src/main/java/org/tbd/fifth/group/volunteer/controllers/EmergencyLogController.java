package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.EmergencyLogModel;
import org.tbd.fifth.group.volunteer.services.EmergencyLogService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class EmergencyLogController {

    private final EmergencyLogService emergencyLogService;

    public EmergencyLogController(EmergencyLogService emergencyLogService) {
        this.emergencyLogService = emergencyLogService;
    }

    @PostMapping("/emergencyLog")
    @ResponseBody
    public EmergencyLogModel createEmergencyLog(@RequestBody EmergencyLogModel emergencyLog) {
        return emergencyLogService.createEmergencyLog(emergencyLog);
    }

    @GetMapping("/emergencyLog/{emergency_log_id}")
    @ResponseBody
    public EmergencyLogModel getEmergencyLog(@PathVariable int emergency_log_id) {
        return emergencyLogService.getEmergencyLog(emergency_log_id);
    }
}
