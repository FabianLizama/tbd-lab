package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.QueryLogModel;
import org.tbd.fifth.group.volunteer.services.QueryLogService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class QueryLogController {

    public final QueryLogService queryLogService;

    public QueryLogController(QueryLogService queryLogService) {
        this.queryLogService = queryLogService;
    }

    @PostMapping("/queryLog")
    @ResponseBody
    public QueryLogModel createQueryLog(@RequestBody QueryLogModel queryLog) {
        return queryLogService.createQueryLog(queryLog);
    }

    @GetMapping("/queryLog/{query_log_id}")
    @ResponseBody
    public QueryLogModel getQueryLog(@PathVariable int query_log_id) {
        return queryLogService.getQueryLog(query_log_id);
    }
}
