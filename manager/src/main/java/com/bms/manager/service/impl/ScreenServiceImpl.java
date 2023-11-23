package com.bms.manager.service.impl;

import javax.persistence.EntityNotFoundException;
import com.bms.manager.domainObject.ScreenResponse;
import com.bms.manager.domainObject.ScreenUpsertRequest;
import com.bms.manager.entity.Screen;
import com.bms.manager.entity.Theatre;
import com.bms.manager.repo.ScreenRepository;
import com.bms.manager.repo.TheatreRepository;
import com.bms.manager.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private ScreenRepository audiRepository;
    @Autowired
    private TheatreRepository theatreRepository;

    @Override
    public ScreenResponse saveAudi(ScreenUpsertRequest request) throws Exception {
        Optional<Theatre> theatre = theatreRepository.findById(request.getTheatreId());
        if (theatre.isEmpty()) {
            throw new EntityNotFoundException("Theatre not found");
        }
        Theatre theatreDb = theatre.get();
        Screen audiSaved = audiRepository.save(new Screen(null, request.getFrontSeats(),
                request.getMiddleSeats(), request.getBackSeats(), request.getName(), theatreDb));

        return new ScreenResponse(audiSaved.getId(), audiSaved.getName(), audiSaved.getFrontSeats(),
                audiSaved.getMiddleSeats(), audiSaved.getBackSeats(),
                audiSaved.getTheatre().getId());
    }

    @Override
    public ScreenResponse getAudi(Integer id) throws Exception {
        Optional<Screen> audi = audiRepository.findById(id);
        if (audi.isEmpty()) {
            throw new EntityNotFoundException("Audi not found");
        }
        Screen audiDb = audi.get();
        return new ScreenResponse(audiDb.getId(), audiDb.getName(), audiDb.getFrontSeats(),
                audiDb.getMiddleSeats(), audiDb.getBackSeats(),
                audiDb.getTheatre().getId());
    }

    @Override
    public ScreenResponse updateAudi(Integer id, ScreenUpsertRequest request) throws Exception {
        Optional<Screen> audi = audiRepository.findById(id);
        if (audi.isEmpty()) {
            throw new EntityNotFoundException("Audi not found");
        }
        Screen audiDb = audi.get();
        Optional<Theatre> theatre = theatreRepository.findById(request.getTheatreId());
        if (theatre.isEmpty()) {
            throw new EntityNotFoundException("Theatre not found");
        }
        Theatre theatreDb = theatre.get();
        audiDb.setName(request.getName());
        audiDb.setTheatre(theatreDb);
        audiDb.setFrontSeats(request.getFrontSeats());
        audiDb.setBackSeats(request.getBackSeats());
        audiDb.setMiddleSeats(request.getMiddleSeats());
        Screen audiSaved = audiRepository.save(audiDb);

        return new ScreenResponse(audiSaved.getId(), audiSaved.getName(), audiSaved.getFrontSeats(),
                audiSaved.getMiddleSeats(), audiSaved.getBackSeats(),
                audiSaved.getTheatre().getId());
    }

    @Override
    public void deleteAudi(Integer id) throws Exception {
        theatreRepository.deleteById(id);
    }
}
