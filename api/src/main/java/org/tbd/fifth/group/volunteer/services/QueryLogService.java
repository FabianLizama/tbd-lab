package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.QueryLogModel;
import org.tbd.fifth.group.volunteer.repositories.QueryLogRepository;

@Repository
public class QueryLogService implements QueryLogRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public QueryLogModel createQueryLog(QueryLogModel queryLog) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery("INSERT INTO \"queries_log\" (user_name, call_date, call_time, query_type, query_statement) VALUES (:user_name, :call_date, :call_time, :query_type, :query_statement)")
                    .addParameter("user_name",queryLog.getUser_name())
                    .addParameter("call_date",queryLog.getCall_date())
                    .addParameter("call_time",queryLog.getCall_time())
                    .addParameter("query_type",queryLog.getQuery_type())
                    .addParameter("query_type",queryLog.getQuery_statement());
            return queryLog;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public QueryLogModel getQueryLog(int query_log_id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT * FROM \"queries_log\" WHERE query_log_id = :query_log_id")
                    .addParameter("query_log_id", query_log_id)
                    .executeAndFetchFirst(QueryLogModel.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
