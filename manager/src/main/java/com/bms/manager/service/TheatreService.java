package com.bms.manager.service;


import com.bms.manager.domainObject.TheatreOnboardRequest;
import com.bms.manager.domainObject.TheatreResponse;

public interface TheatreService {
    TheatreResponse saveTheatre(TheatreOnboardRequest request) throws Exception;

    TheatreResponse getTheatre(Integer id) throws Exception;

    TheatreResponse updateTheatre(Integer id, TheatreOnboardRequest request) throws Exception;

    void deleteTheatre(Integer id) throws Exception;
}
