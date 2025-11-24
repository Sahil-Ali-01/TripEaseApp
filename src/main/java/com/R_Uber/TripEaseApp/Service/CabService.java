package com.R_Uber.TripEaseApp.Service;

import com.R_Uber.TripEaseApp.DTO.request.CabRequest;
import com.R_Uber.TripEaseApp.DTO.response.CabResponse;
import com.R_Uber.TripEaseApp.Model.Cab;
import com.R_Uber.TripEaseApp.Model.Driver;
import com.R_Uber.TripEaseApp.Repository.DriverRepository;
import com.R_Uber.TripEaseApp.exception.DriverNotFoundException;
import com.R_Uber.TripEaseApp.transformer.CabTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CabService {

    @Autowired
    DriverRepository driverRepository;

    public CabResponse registerCab(CabRequest cabRequest, int driverId) {

        //check where driver exist or not
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if(optionalDriver.isEmpty()){
            throw new DriverNotFoundException("Invalid Driver Id");
        }

        // if Driver Exist
        Driver driver = optionalDriver.get();

        // requestDTO to entity
        Cab cab = CabTransformer.cabRequestToCab(cabRequest);
        // have to set cab to driver
        driver.setCab(cab);

        Driver savedDriver = driverRepository.save(driver); //this will save both driver and cab cz of cascading from parent to child

        // Entity to DTO

        return CabTransformer.cabToCabResponse(savedDriver.getCab(), savedDriver);



    }
}
