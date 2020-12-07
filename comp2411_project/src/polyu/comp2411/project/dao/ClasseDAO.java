package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Teacher;

public interface ClasseDAO {
    Classe searchById(int id);

    Classe searchByClassTeacher(Teacher tc);
}
