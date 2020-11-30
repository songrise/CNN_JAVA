package polyu.comp2411.project.entity;

public class Teacher {
    private int tId;

    public Teacher(int tId, String tName) {
        this.tId = tId;
        this.tName = tName;
    }

    private String tName;

    public int getTId() {
        return tId;
    }

    public void setTId(int tId) {
        this.tId = tId;
    }

    public String getTName() {
        return tName;
    }

    public void setTName(String tName) {
        this.tName = tName;
    }
}
