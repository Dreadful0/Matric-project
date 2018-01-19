package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.model.dao.connection.IConnectionFactory;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.MySqlGenericDao;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers.CourseMapper;
import com.demianenko.application.model.dao.interfaces.daoInt.ICourseDao;
import com.demianenko.application.model.entities.Course;

import java.util.List;

public class MySqlCourseDao implements ICourseDao {

    private IConnectionFactory cf;
    private MySqlGenericDao gDao;

    public MySqlCourseDao(IConnectionFactory connectionFactory) {
        this.cf = connectionFactory;
        gDao = new MySqlGenericDao(cf);
    }

    @Override
    public Integer add(Course course) {
        return gDao.insert("INSERT INTO course " +
                        "(name)" +
                        "VALUES(?)",
                course.getName());
    }

    @Override
    public Course find(Integer id) {
        Course course;
        course = gDao.findObject("SELECT * FROM course " +
                "WHERE id=?", CourseMapper::map, id);
        return course;
    }

    @Override
    public void update(Course course) {
        gDao.update("UPDATE course " +
                        "SET " +
                        "name = ? " +
                        "WHERE id=?",
                course.getName(),
                course.getId());
    }

    @Override
    public void delete(Integer id) {
        gDao.remove("DELETE FROM course " +
                "WHERE id=?", id);
    }

    @Override
    public List<Course> findAll() {
        List<Course> list = gDao.findObjects("SELECT * FROM course "
                , CourseMapper::map);
        return list;
    }
}
