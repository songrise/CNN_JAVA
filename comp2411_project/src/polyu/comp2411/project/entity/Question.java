package polyu.comp2411.project.entity;

public class Question {
    private int qNo;
    private int testId;
    private String qDescri;
    private Boolean compulsory;
    private String type;
    private String ans;

    public Question(int qNo, int testId, String qDescri, Boolean compulsory, String type, String ans, int score) {
        this.qNo = qNo;
        this.testId = testId;
        this.qDescri = qDescri;
        this.compulsory = compulsory;
        this.type = type;
        this.ans = ans;
        this.score = score;
    }

    private int score;

    public int getqNo() {
        return qNo;
    }

    public void setqNo(int qNo) {
        this.qNo = qNo;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getqDescri() {
        return qDescri;
    }

    public void setqDescri(String qDescri) {
        this.qDescri = qDescri;
    }

    public Boolean getCompulsory() {
        return compulsory;
    }

    public void setCompulsory(Boolean compulsory) {
        this.compulsory = compulsory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
