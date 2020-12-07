package polyu.comp2411.project.entity;

public class ScoreList implements Cloneable {

    private int stuId;
    private int testId;
    private int score;

    public ScoreList(int stuId, int testId, int score, String feedBack) {
        this.stuId = stuId;
        this.testId = testId;
        this.score = score;
        this.feedBack = feedBack;
    }

    private String feedBack;

    public int getStuId() {
        return stuId;
    }

    public int getTestId() {
        return testId;
    }

    public int getScore() {
        return score;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public String getFeedBack() {
        return feedBack;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
