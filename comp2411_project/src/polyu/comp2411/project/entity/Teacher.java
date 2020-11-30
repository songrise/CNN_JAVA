package polyu.comp2411.project.entity;

public class Teacher {
    private int tId;

    public Teacher(int tId, String tName) {
        this.tId = tId;
        this.tName = tName;
    }

    private String tName;

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
}
