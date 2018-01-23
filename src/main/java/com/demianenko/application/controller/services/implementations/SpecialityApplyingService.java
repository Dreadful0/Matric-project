package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.Speciality;
import com.demianenko.application.model.entities.SpecialityRequest;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import java.util.List;

public class SpecialityApplyingService {

    private final static Logger LOGGER = Logger.getLogger(SpecialityApplyingService.class);

    private IDaoFactory daoFactory;

    public SpecialityApplyingService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void applyForSpeciality(Integer specialityId, List<Integer> examResultIdList, User user){
        SpecialityRequest specialityRequest = new SpecialityRequest();
        int finalMark = 0;
        for (Integer examResultId: examResultIdList) {
            finalMark+=daoFactory.getExamResultDao().find(examResultId).getMark();
        }
        specialityRequest.setFinalMark(finalMark);
        specialityRequest.setSpecialityId(specialityId);
        specialityRequest.setUserId(user.getId());
        daoFactory.getSpecialityRequestDao().add(specialityRequest);
    }

    public List<SpecialityRequest> getUserSpecialityRequests(User user){
        List<SpecialityRequest> specialityRequests = daoFactory.getSpecialityRequestDao()
                .getSpecialityRequestByUserId(user.getId());
        for (SpecialityRequest request: specialityRequests) {
            request.setSpeciality(daoFactory.getSpecialityDao().find(request.getSpecialityId()));
        }
        return specialityRequests;
    }

    public void processRatings(){
        List<Speciality> specialities = daoFactory.getSpecialityDao().findAll();
        for (Speciality speciality: specialities) {
            List<SpecialityRequest> requests = daoFactory.getSpecialityRequestDao()
                    .getSpecialityRequestBySpecialityId(speciality.getId());
            int delimeter = speciality.getStudentsNumber();
            if(delimeter < requests.size()){
                while (requests.get(delimeter).getFinalMark() == requests.get(delimeter+1).getFinalMark()){
                    delimeter--;
                }
            }
            for (SpecialityRequest request: requests) {
                if (delimeter > 0) {
                    request.setConfirmed("Confirmed");
                } else {
                    request.setConfirmed("Rejected");
                }
                daoFactory.getSpecialityRequestDao().update(request);
            }
        }
    }
}
