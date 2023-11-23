package com.bms.manager.service;


import com.bms.manager.domainObject.MovieResponse;
import com.bms.manager.domainObject.MovieUpsertRequest;

public interface MovieService {
    MovieResponse saveMovie(MovieUpsertRequest request) throws Exception;

    MovieResponse getMovie(Integer id) throws Exception;

    MovieResponse updateMovie(Integer id, MovieUpsertRequest request) throws Exception;

    void deleteMovie(Integer id) throws Exception;
}
