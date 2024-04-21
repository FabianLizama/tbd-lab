package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.RankingModel;

public interface RankingRepository {
    public RankingModel createRanking(RankingModel ranking);

    public RankingModel getRanking(int ranking_id);

    public String createRankingByVolunteerIdAndTaskId(int volunteer_id, int task_id);
}
