package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.transaction.Transaction;
import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.Course;
import com.demianenko.application.model.entities.ExamResult;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Groups business logic for passing exams
 */
public class ExamService {

    private final static Logger LOGGER = Logger.getLogger(UniversityService.class);

    private IDaoFactory daoFactory;

    public ExamService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Performs registration for passing exams
     *
     * @param coursesIdList list of courses ids to registration
     * @param user User
     * @throws UserInfoException if User already registered for one of courses in list
     */
    public void registerForExams(List<Integer> coursesIdList, User user) throws UserInfoException {
        List<ExamResult> usersExams =  getNotVerifiedUsersExamResults(user);
        List<Integer> userSubscribedCourses = usersExams.stream()
                .mapToInt(ExamResult::getCourseId).boxed().collect(Collectors.toList());
        if(!Collections.disjoint(userSubscribedCourses,coursesIdList)){
            throw new UserInfoException("alreadyRegistered");
        }
        Transaction.doTransaction(()->{
            for (Integer courseId: coursesIdList) {
                ExamResult examResult = new ExamResult();
                examResult.setUserId(user.getId());
                examResult.setCourseId(courseId);
                daoFactory.getExamResultDao().add(examResult);
            }
            user.setExamAttempts(user.getExamAttempts()-coursesIdList.size());
            daoFactory.getUserDao().update(user);
        });
    }

    /**
     * Returns verified Users exam results entities with loaded courses
     *
     * @param user User
     * @return list of ExamResult
     */
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

    /**
     * Returns not verified Users exam results entities
     *
     * @param user User
     * @return list of ExamResult
     */
    public List<ExamResult> getNotVerifiedUsersExamResults(User user){
        return daoFactory.getExamResultDao().getNotVerifiedExamResultsByUserId(user.getId());
    }

    /**
     * Returns not verified exam results by Course id
     *
     * @param courseId Course id
     * @return list of ExamResult
     */
    public List<ExamResult> getNotVerifiedExamResultsByCourse(Integer courseId){
        return daoFactory.getExamResultDao().getNotVerifiedExamResultsByCourseId(courseId);
    }

    /**
     * Performs verifying of exam result
     *
     * @param mark students mark
     * @param examResultId ExamResult id
     */
    public void setMark(Integer mark, Integer examResultId){
        ExamResult examResult = daoFactory.getExamResultDao().find(examResultId);
        examResult.setMark(mark);
        examResult.setDate(new java.util.Date());
        daoFactory.getExamResultDao().update(examResult);
    }
}
