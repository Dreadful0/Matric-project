package com.demianenko.application.model.dao.interfaces.daoInt;

import com.demianenko.application.model.entities.SpecialityRequest;

import java.util.List;

public interface ISpecialityRequestDao extends IGenericDao<SpecialityRequest, Integer> {

    List<SpecialityRequest> getSpecialityRequestByUserId(Integer userId);

    List<SpecialityRequest> getSpecialityRequestBySpecialityId(Integer specialityId);

}
