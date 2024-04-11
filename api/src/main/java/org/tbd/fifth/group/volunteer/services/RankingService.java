package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.RankingModel;
import org.tbd.fifth.group.volunteer.repositories.RankingRepository;

@Repository
public class RankingService implements RankingRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public RankingModel createRanking(RankingModel ranking){
        try(Connection connection = sql2o.open()){
            connection.createQuery("INSERT INTO \"ranking\" ( volunteer_id, task_id, grade) VALUES ( :volunteer_id, :task_id, :grade)")

                    .addParameter("volunteer_id", ranking.getVolunteer_id())
                    .addParameter("task_id", ranking.getTask_id())
                    .addParameter("grade", ranking.getGrade())
                    .executeUpdate();

            return ranking;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public RankingModel getRanking(int ranking_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"ranking\" WHERE ranking_id = :ranking_id")
                    .addParameter("ranking_id", ranking_id)
                    .executeAndFetchFirst(RankingModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
