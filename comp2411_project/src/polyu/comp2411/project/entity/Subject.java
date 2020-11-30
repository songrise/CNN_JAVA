package polyu.comp2411.project.entity;

public class Subject {
    public Subject(int subId, String subName) {
        this.subId = subId;
        this.subName = subName;
    }

    private int subId;
    private String subName;


    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
