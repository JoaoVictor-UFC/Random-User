package com.joao.victor.random.user.v1.service;

import com.joao.victor.random.user.v1.dtos.CoordinatesRequest;
import com.joao.victor.random.user.v1.dtos.CreateLocationRequest;
import com.joao.victor.random.user.v1.dtos.TimeZoneRequest;
import com.joao.victor.random.user.v1.entities.CoordinatesEntity;
import com.joao.victor.random.user.v1.entities.LocationEntity;
import com.joao.victor.random.user.v1.entities.TimezoneEntity;
import com.joao.victor.random.user.v1.repository.CoordinatesRepository;
import com.joao.victor.random.user.v1.repository.LocationRepository;
import com.joao.victor.random.user.v1.repository.TimezoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LocationService {

    @Autowired private LocationRepository locationRepository;

    @Autowired private CoordinatesRepository coordinatesRepository;

    @Autowired private TimezoneRepository timezoneRepository;

    public LocationEntity saveLocation(CreateLocationRequest request){
        LocationEntity entity = new LocationEntity();
        entity.setStreet(request.getStreet());
        entity.setCity(request.getCity());
        entity.setState(request.getState());
        entity.setPostcode(request.getPostcode());
        entity.setCoordinates(saveCoordinates(request.getCoordinates()));
        entity.setTimezone(saveTimeZone(request.getTimezone()));
        return locationRepository.save(entity);
    }

    public CoordinatesEntity saveCoordinates(CoordinatesRequest request){
        CoordinatesEntity entity = new CoordinatesEntity();
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
        return coordinatesRepository.save(entity);
    }

    public TimezoneEntity saveTimeZone(TimeZoneRequest request){
        TimezoneEntity entity = new TimezoneEntity();
        entity.setDescription(request.getDescription());
        entity.setOffSet(request.getOffset());
        return timezoneRepository.save(entity);
    }
}
