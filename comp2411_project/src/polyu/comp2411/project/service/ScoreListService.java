package polyu.comp2411.project.service;

import polyu.comp2411.project.entity.ScoreList;

import java.util.List;

public interface ScoreListService {
    List<ScoreList> getScoreList(int stuID);
}
