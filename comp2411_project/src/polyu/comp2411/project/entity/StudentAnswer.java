package polyu.comp2411.project.entity;


// this means the answer a student made for any exams
public class StudentAnswer implements Cloneable{
    private int stuId;
    private int testId;
    private int queNo;
    private String answer;
    private int score;

    public StudentAnswer(int stuId, int testId, int queNo, String answer, int score) {
        this.stuId = stuId;
        this.testId = testId;
        this.queNo = queNo;
        this.answer = answer;
        this.score = score;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getQueNo() {
        return queNo;
    }

    public void setQueNo(int queNo) {
        this.queNo = queNo;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
