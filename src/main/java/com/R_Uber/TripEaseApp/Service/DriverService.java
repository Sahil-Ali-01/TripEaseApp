package com.R_Uber.TripEaseApp.Service;

import com.R_Uber.TripEaseApp.DTO.request.DriverRequest;
import com.R_Uber.TripEaseApp.DTO.response.DriverResponse;
import com.R_Uber.TripEaseApp.Model.Driver;
import com.R_Uber.TripEaseApp.Repository.DriverRepository;
import com.R_Uber.TripEaseApp.transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    public DriverResponse addDriver(DriverRequest driverRequest) {

        // DTO to Entity
        Driver driver = DriverTransformer.driverRequesttoDriver(driverRequest);

        // saving the Entity in db
        Driver savedDriver  = driverRepository.save(driver);

        // Entity to DTO
        return DriverTransformer.driverToDriverResponse(savedDriver);
    }
}
