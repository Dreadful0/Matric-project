package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.controller.util.converters.SqlToUtilDateConverter;
import com.demianenko.application.model.dao.connection.IConnectionFactory;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.MySqlGenericDao;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers.ExamResultMapper;
import com.demianenko.application.model.dao.interfaces.daoInt.IExamResultDao;
import com.demianenko.application.model.entities.ExamResult;
import com.demianenko.application.model.entities.Role;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class MySqlExamResultDao implements IExamResultDao {

    private final static Logger LOGGER = Logger.getLogger(MySqlExamResultDao.class);

    private IConnectionFactory cf;
    private MySqlGenericDao gDao;

    public MySqlExamResultDao(IConnectionFactory connectionFactory) {
        this.cf = connectionFactory;
        gDao = new MySqlGenericDao(cf);
    }

    @Override
    public Integer add(ExamResult examResult) {
        return gDao.insert("INSERT INTO exam_result " +
                        "(date," +
                        "mark," +
                        "course_id," +
                        "user_id)" +
                        "VALUES(?,?,?,?)",
                SqlToUtilDateConverter.getSql(examResult.getDate()),
                examResult.getMark(),
                examResult.getCourseId(),
                examResult.getUserId());
    }

    @Override
    public ExamResult find(Integer id) {
        ExamResult examResult;
        examResult = gDao.findObject("SELECT * FROM exam_result " +
                "WHERE id=?", ExamResultMapper::map, id);
        return examResult;
    }

    @Override
    public void update(ExamResult examResult) {
        gDao.update("UPDATE exam_result " +
                        "SET " +
                        "date = ?," +
                        "mark = ?," +
                        "course_id = ?," +
                        "user_id = ? " +
                        "WHERE id=?",
                SqlToUtilDateConverter.getSql(examResult.getDate()),
                examResult.getMark(),
                examResult.getCourseId(),
                examResult.getUserId(),
                examResult.getId());
    }

    @Override
    public void delete(Integer id) {
        gDao.remove("DELETE FROM exam_result " +
                "WHERE id=?", id);
    }

    @Override
    public List<ExamResult> findAll() {
        List<ExamResult> list = gDao.findObjects("SELECT * FROM exam_result "
                , ExamResultMapper::map);
        return list;
    }

    @Override
    public List<ExamResult> getVerifiedExamResultsByUserId(Integer userId) {
        List<ExamResult> list = gDao.findObjects("SELECT * FROM exam_result "+
                "WHERE mark IS NOT NULL AND user_id=?" , ExamResultMapper::map, userId);
        return list;
    }

    @Override
    public List<ExamResult> getNotVerifiedExamResultsByUserId(Integer userId) {
        List<ExamResult> list = gDao.findObjects("SELECT * FROM exam_result "+
                "WHERE mark IS NULL AND user_id=?" , ExamResultMapper::map, userId);
        return list;
    }

    @Override
    public List<ExamResult> getNotVerifiedExamResultsByCourseId(Integer courseId) {
        List<ExamResult> list = gDao.findObjects("SELECT * FROM exam_result "+
                "INNER JOIN user ON exam_result.user_id = user.id "+
                "WHERE exam_result.mark IS NULL AND course_id=? order by user.second_name" , (rs)->{
            ExamResult examResult = new ExamResult();
            try {
                examResult.setId(rs.getInt("exam_result.id"));
                examResult.setDate(SqlToUtilDateConverter.getUtil(rs.getDate("exam_result.date")));
                examResult.setMark(rs.getInt("exam_result.mark"));
                examResult.setCourseId(rs.getInt("exam_result.course_id"));
                examResult.setUserId(rs.getInt("exam_result.user_id"));
            } catch (SQLException e){
                LOGGER.error("Can not map ExamResult", e);
                return null;
            }
            User user = new User();
            try {
                user.setEmail(rs.getString("user.email"));
                user.setFirstName(rs.getString("user.first_name"));
                user.setId(rs.getInt("user.id"));
                user.setPassword(rs.getString("user.password"));
                user.setSecondName(rs.getString("user.second_name"));
                user.setRole(Role.getRole(rs.getString("user.role")));
                user.setExamAttempts(rs.getInt("user.exam_attempts"));
            } catch (SQLException e){
                LOGGER.error("Can not map User", e);
                return null;
            }
            examResult.setUser(user);
            return examResult;
        }, courseId);
        return list;
    }
}
