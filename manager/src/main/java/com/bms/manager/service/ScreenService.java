package com.bms.manager.service;


import com.bms.manager.domainObject.ScreenResponse;
import com.bms.manager.domainObject.ScreenUpsertRequest;

public interface ScreenService {
    ScreenResponse saveAudi(ScreenUpsertRequest request) throws Exception;

    ScreenResponse getAudi(Integer id) throws Exception;

    ScreenResponse updateAudi(Integer id, ScreenUpsertRequest request) throws Exception;

    void deleteAudi(Integer id) throws Exception;
}
