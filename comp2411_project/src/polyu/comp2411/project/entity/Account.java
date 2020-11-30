package polyu.comp2411.project.entity;

public class Account {
    public Account(int uid, String password, int privilege) {
        this.uid = uid;
        this.password = password;
        this.privilege = privilege;
    }

    private int uid;
    private String password;
    private int privilege;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}


