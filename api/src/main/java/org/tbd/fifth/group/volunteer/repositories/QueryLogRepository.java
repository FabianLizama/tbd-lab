package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.QueryLogModel;

public interface QueryLogRepository {
    public QueryLogModel createQueryLog(QueryLogModel queryLog);

    public QueryLogModel getQueryLog(int query_log_id);
}
