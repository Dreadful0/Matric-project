package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.controller.util.converters.SqlToUtilDateConverter;
import com.demianenko.application.model.dao.connection.IConnectionFactory;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.MySqlGenericDao;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers.ExamResultMapper;
import com.demianenko.application.model.dao.interfaces.daoInt.IExamResultDao;
import com.demianenko.application.model.entities.ExamResult;

import java.util.List;

public class MySqlExamResultDao implements IExamResultDao {

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
}
