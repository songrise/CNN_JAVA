package polyu.comp2411.project.dao;

import java.util.List;

public interface LoggerDAO {
    void addLog(String info);

    List<String> getAll();
}
