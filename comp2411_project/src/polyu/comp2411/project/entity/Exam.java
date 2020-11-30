package polyu.comp2411.project.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

public class Exam {
    private int testId;
    private BigInteger testDuration;
    private Timestamp startTime;
    private int classNo;
    private int subjectId;

    public Exam(int testId, BigInteger testDuration, Timestamp startTime, int classNo, int subjectId, int arrangerId) {
        this.testId = testId;
        this.testDuration = testDuration;
        this.startTime = startTime;
        this.classNo = classNo;
        this.subjectId = subjectId;
        this.arrangerId = arrangerId;
    }

    private int arrangerId;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public BigInteger getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(BigInteger testDuration) {
        this.testDuration = testDuration;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getArrangerId() {
        return arrangerId;
    }

    public void setArrangerId(int arrangerId) {
        this.arrangerId = arrangerId;
    }
}
