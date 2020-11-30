package polyu.comp2411.project.entity;

public class ExamList {
    private int stuId;

    public ExamList(int stuId, int test_id) {
        this.stuId = stuId;
        this.testId = test_id;
    }

    private int testId;

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
}
