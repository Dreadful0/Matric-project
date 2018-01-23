package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.Course;
import com.demianenko.application.model.entities.ExamResult;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ExamService {

    private final static Logger LOGGER = Logger.getLogger(UniversityService.class);

    private IDaoFactory daoFactory;

    public ExamService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void registerForExams(List<Integer> coursesIdList, User user) throws UserInfoException {
        List<ExamResult> usersExams =  getNotVerifiedUsersExamResults(user);
        List<Integer> userSubscribedCourses = usersExams.stream()
                .mapToInt(ExamResult::getCourseId).boxed().collect(Collectors.toList());
        if(!Collections.disjoint(userSubscribedCourses,coursesIdList)){
            throw new UserInfoException("AlreadyRegistered");
        }
        for (Integer courseId: coursesIdList) {
            ExamResult examResult = new ExamResult();
            examResult.setUserId(user.getId());
            examResult.setCourseId(courseId);
            daoFactory.getExamResultDao().add(examResult);
        }
        user.setExamAttempts(user.getExamAttempts()-coursesIdList.size());
        daoFactory.getUserDao().update(user);
    }

    public List<ExamResult> getVerifiedUsersExamResults(User user){
        List<ExamResult> examResults = daoFactory.getExamResultDao()
                .getVerifiedExamResultsByUserId(user.getId());
        List<Course> courses = daoFactory.getCourseDao().findAll();
        for (ExamResult examResult:examResults) {
            for (Course course: courses) {
                if(course.getId()==examResult.getCourseId()){
                    examResult.setCourse(course);
                    break;
                }
            }
        }
        return examResults;
    }

    public List<ExamResult> getNotVerifiedUsersExamResults(User user){
        return daoFactory.getExamResultDao().getNotVerifiedExamResultsByUserId(user.getId());
    }

    public List<ExamResult> getNotVerifiedExamResultsByCourse(Integer courseId){
        return daoFactory.getExamResultDao().getNotVerifiedExamResultsByCourseId(courseId);
    }

    public void setMark(Integer mark, Integer examResultId){
        ExamResult examResult = daoFactory.getExamResultDao().find(examResultId);
        examResult.setMark(mark);
        examResult.setDate(new java.util.Date());
        daoFactory.getExamResultDao().update(examResult);
    }
}
