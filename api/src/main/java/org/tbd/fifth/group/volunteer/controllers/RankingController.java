package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.RankingModel;
import org.tbd.fifth.group.volunteer.services.RankingService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RankingController {

    public final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @PostMapping("/ranking")
    @ResponseBody
    public RankingModel createRanking(@RequestBody RankingModel ranking) {
        return rankingService.createRanking(ranking);
    }

    @GetMapping("/ranking/{ranking_id}")
    @ResponseBody
    public RankingModel getRanking(@PathVariable int ranking_id) {
        return rankingService.getRanking(ranking_id);
    }
}
