package com.demianenko.application.model.dao.interfaces.daoInt;

import com.demianenko.application.model.entities.ExamResult;

import java.util.List;

public interface IExamResultDao extends IGenericDao<ExamResult, Integer> {
    List<ExamResult> getVerifiedExamResultsByUserId(Integer userId);

    List<ExamResult> getNotVerifiedExamResultsByUserId(Integer userId);

    List<ExamResult> getNotVerifiedExamResultsByCourseId(Integer courseId);
}
