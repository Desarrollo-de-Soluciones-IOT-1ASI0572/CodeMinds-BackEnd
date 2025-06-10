package com.codeminds.edugo.profiles.application.internal.queryservices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeminds.edugo.profiles.domain.model.entities.Driver;
import com.codeminds.edugo.profiles.domain.model.queries.GetAllDriversQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetDriverByIdQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetDriverByUserIdQuery;
import com.codeminds.edugo.profiles.domain.services.DriverQueryService;
import com.codeminds.edugo.profiles.infrastructure.persistence.jpa.DriverRepository;

@Service
public class DriverQueryServiceImpl implements DriverQueryService {

    private final DriverRepository driverRepository;

    public DriverQueryServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Optional<Driver> handle(GetDriverByIdQuery query) {
        return driverRepository.findById(query.id());
    }

    @Override
    public Optional<Driver> handle(GetDriverByUserIdQuery query) {
        return driverRepository.findByUser_Id(query.user_id());
    }

    @Override
    public List<Driver> handle(GetAllDriversQuery query) {
        return driverRepository.findAll();
    }

}
