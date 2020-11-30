package polyu.comp2411.project.entity;

public class ScoreList {
    private int stu_id;
    private int test_id;
    private int score;

    public ScoreList(int stu_id, int test_id, int score, String feedBack) {
        this.stu_id = stu_id;
        this.test_id = test_id;
        this.score = score;
        this.feedBack = feedBack;
    }

    private String feedBack;

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }
}
