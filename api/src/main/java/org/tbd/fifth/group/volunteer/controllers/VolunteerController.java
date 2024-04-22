package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.tbd.fifth.group.volunteer.models.VolunteerModel;
import org.tbd.fifth.group.volunteer.services.VolunteerService;



@CrossOrigin
@RestController
@RequestMapping("/api")
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

    @GetMapping("/volunteer/{volunteer_id}")
    @ResponseBody
    public VolunteerModel getVolunteer(@PathVariable int volunteer_id) {
        return volunteerService.getVolunteer(volunteer_id);
    }

    @GetMapping("/volunteers/getVolunteerIdByUserId/{user_id}")
    @ResponseBody
    public ResponseEntity<Integer> getVolunteerIdByUserId(@PathVariable int user_id) {
        return volunteerService.getVolunteerIdByUserId(user_id);
    }
}
