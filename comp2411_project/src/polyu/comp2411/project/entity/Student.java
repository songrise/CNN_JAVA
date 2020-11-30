package polyu.comp2411.project.entity;

public class Student {
    private int id;
    private String name;
    private int classNo;

    public Student(int id, String name,int classNo) {
        this.id = id;
        this.name = name;
        this.classNo=classNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public int getClassNo() { return classNo; }
}

