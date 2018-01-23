package com.demianenko.application.model.dao.interfaces.factoryInt;

import com.demianenko.application.model.dao.interfaces.daoInt.*;

public interface IDaoFactory {

    ICourseDao getCourseDao();

    IExamResultDao getExamResultDao();

    ISpecialityDao getSpecialityDao();

    IUniversityDao getUniversityDao();

    IUserDao getUserDao();

    ISpecialityRequestDao getSpecialityRequestDao();
}
