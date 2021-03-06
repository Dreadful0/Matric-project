package com.demianenko.application.controller.command;

import com.demianenko.application.controller.command.implementations.*;

public enum CommandList {
    AUTHORIZATION(new AuthorizationHandler()),
    REGISTRATION(new RegistrationHandler()),
    PERSONAL_INFO(new PersonalInfoHandler()),
    LOGOUT(new LogoutHandler()),
    LOCALIZATION(new LocalizationHandler()),
    ADMIN_PAGE_INFO(new AdminPageInfoHandler()),
    ADD_COURSE(new AddCourseHandler()),
    DELETE_COURSE(new DeleteCourseHandler()),
    MODIFY_COURSE(new ModifyCourseHandler()),
    ADD_UNIVERSITY(new AddUniversityHandler()),
    DELETE_UNIVERSITY(new DeleteUniversityHandler()),
    MODIFY_UNIVERSITY(new ModifyUniversityHandler()),
    DELETE_SPECIALITY(new DeleteSpecialityHandler()),
    ADD_SPECIALITY(new AddSpecialityHandler()),
    CHANGE_ROLE(new ChangeUserRoleHandler()),
    COURSE_REGISTRATION_PAGE_INFO(new CourseRegistrationPageInfoHandler()),
    SPECIALITY_REGISTRATION_PAGE_INFO(new SpecialityRegistrationPageInfoHandler()),
    REGISTER_FOR_EXAM(new RegisterForExamHandler()),
    SET_MARK(new SetExamMarkHandler()),
    APPLY_FOR_SPECIALITY(new ApplyForSpecialityHandler()),
    SET_EXAM_ATTEMPTS(new SetAttemptsHandler()),
    PROCESS_RATINGS(new ProcessRatingsHandler()),
    REDIRECT(new RedirectHandler());

    private ICommand command;

    private CommandList(ICommand command){
        this.command = command;
    }

    public ICommand getCommand(){
        return command;
    }
}
