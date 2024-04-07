package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.VolunteerModel;
import org.tbd.fifth.group.volunteer.services.VolunteerService;

@CrossOrigin
@RestController
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping("/volunteer")
    @ResponseBody
    public VolunteerModel createVolunteer(@RequestBody VolunteerModel volunteer) {
        return volunteerService.createVolunteer(volunteer);
    }
}
