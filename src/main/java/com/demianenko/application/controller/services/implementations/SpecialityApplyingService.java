package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.controller.util.constants.SessionParameters;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.transaction.Transaction;
import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.Speciality;
import com.demianenko.application.model.entities.SpecialityRequest;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Groups business logic for applying for speciality
 */
public class SpecialityApplyingService {

    private final static Logger LOGGER = Logger.getLogger(SpecialityApplyingService.class);
    private final String CONFIRMED = "confirmed";
    private final String REJECTED = "rejected";

    private IDaoFactory daoFactory;

    public SpecialityApplyingService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Performs applying for speciality
     *
     * @param specialityId id of Speciality to apply
     * @param examResultIdList list of students exam result ids
     * @param user User
     * @throws UserInfoException if reached maximum number of requests
     * or if this user have already applied for this speciality
     */
    public void applyForSpeciality(Integer specialityId, List<Integer> examResultIdList, User user) throws UserInfoException {
        List<SpecialityRequest> userSpecialityRequests = daoFactory.getSpecialityRequestDao()
                .getSpecialityRequestByUserId(user.getId());
        if(userSpecialityRequests.size() >= SessionParameters.SPECIALITY_REQUESTS_LIMIT){
            throw new UserInfoException("maxSpecialityRequests");
        }
        for (SpecialityRequest sReq: userSpecialityRequests) {
            if(sReq.getSpecialityId() == specialityId){
                throw new UserInfoException("alreadyApplied");
            }
        }

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

    /**
     * Returns Users speciality requests entity with loaded Speciality
     *
     * @param user User
     * @return List of SpecialityRequests
     */
    public List<SpecialityRequest> getUserSpecialityRequests(User user){
        List<SpecialityRequest> specialityRequests = daoFactory.getSpecialityRequestDao()
                .getSpecialityRequestByUserId(user.getId());
        for (SpecialityRequest request: specialityRequests) {
            request.setSpeciality(daoFactory.getSpecialityDao().find(request.getSpecialityId()));
        }
        return specialityRequests;
    }

    /**
     * Performs processing students ratings
     * Sets CONFIRMED in students request if he accepted
     * else sets REJECTED
     *
     * @throws UserInfoException if transaction rollback failed
     */
    public void processRatings() throws UserInfoException {
        Transaction.doTransaction(()->{
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
                        request.setConfirmed(CONFIRMED);
                    } else {
                        request.setConfirmed(REJECTED);
                    }
                    daoFactory.getSpecialityRequestDao().update(request);
                }
            }
        });
    }
}
