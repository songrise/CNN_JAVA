package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.TeacherDAO;
import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Teacher;

import java.util.List;

public class TeacherDAOImpl extends BaseDAO  implements TeacherDAO {
    @Override
    public Teacher serchByID(int id) {
        return null;
    }

    @Override
    public void addTeacher(Teacher tc) {

    }

    @Override
    public List<Teacher> searchByClass(Classe cls) {
        return null;
    }

    @Override
    public Teacher searchByClassTeacherOf(Classe cls) {
        return null;
    }
}
