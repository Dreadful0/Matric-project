package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.model.dao.connection.IConnectionFactory;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.MySqlGenericDao;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers.SpecialityMapper;
import com.demianenko.application.model.dao.interfaces.daoInt.ISpecialityDao;
import com.demianenko.application.model.entities.Course;
import com.demianenko.application.model.entities.Speciality;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class MySqlSpecialityDao implements ISpecialityDao {

    private final static Logger LOGGER = Logger.getLogger(MySqlSpecialityDao.class);

    private IConnectionFactory cf;
    private MySqlGenericDao gDao;

    public MySqlSpecialityDao(IConnectionFactory connectionFactory) {
        this.cf = connectionFactory;
        gDao = new MySqlGenericDao(cf);
    }

    @Override
    public Integer add(Speciality speciality) {
        return gDao.insert("INSERT INTO speciality " +
                        "(name," +
                        "students_number," +
                        "university_id)" +
                        "VALUES(?,?,?)",
                speciality.getName(),
                speciality.getStudentsNumber(),
                speciality.getUniversityId());
    }

    @Override
    public Speciality find(Integer id) {
        Speciality speciality;
        speciality = gDao.findObject("SELECT * FROM speciality " +
                "WHERE id=?", SpecialityMapper::map, id);
        return speciality;
    }

    @Override
    public void update(Speciality speciality) {
        gDao.update("UPDATE speciality " +
                        "SET " +
                        "name = ?," +
                        "students_number = ?," +
                        "university_id = ? " +
                        "WHERE id=?",
                speciality.getName(),
                speciality.getStudentsNumber(),
                speciality.getUniversityId(),
                speciality.getId());
    }

    @Override
    public void delete(Integer id) {
        gDao.remove("DELETE FROM speciality " +
                "WHERE id=?", id);
    }

    @Override
    public List<Speciality> findAll() {
        List<Speciality> list = gDao.findObjects("SELECT * FROM speciality "
                , SpecialityMapper::map);
        return list;
    }

    @Override
    public List<Speciality> getSpecialitiesByUniversityId(Integer id) {
        List<Speciality> list = gDao.findObjects("SELECT * FROM speciality "+
                "WHERE university_id = ?"
                , SpecialityMapper::map, id);
        return list;
    }

    @Override
    public List<Course> getRequiredCoursesList(Integer specialityId) {
        List<Course> list = gDao.findObjects(
                "SELECT course.id, course.name "+
                "FROM speciality_courses "+
                "INNER JOIN course "+
                "ON speciality_courses.course_id = course.id "+
                "INNER JOIN speciality "+
                "ON speciality_courses.speciality_id = speciality.id "+
                "WHERE speciality.id = ?"
                , (rs -> {
                    Course course = new Course();
                    try {
                        course.setId(rs.getInt("course.id"));
                        course.setName(rs.getString("course.name"));
                    } catch (SQLException e){
                        LOGGER.error("Can not map Course", e);
                        return null;
                    }
                    return course;
                }),specialityId);
        return list;
    }

    @Override
    public void addCourse(Integer specialityId, Integer courseId) {
        gDao.insert("INSERT INTO speciality_courses " +
                        "(speciality_id," +
                        "course_id)" +
                        "VALUES(?,?)",
                specialityId,
                courseId);
    }
}
