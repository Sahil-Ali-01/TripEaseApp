package com.R_Uber.TripEaseApp.transformer;

import com.R_Uber.TripEaseApp.DTO.request.DriverRequest;
import com.R_Uber.TripEaseApp.DTO.response.DriverResponse;
import com.R_Uber.TripEaseApp.Model.Driver;

public class DriverTransformer {

   // requestDTO to Entity
    public static Driver driverRequesttoDriver(DriverRequest driverRequest){

        return Driver.builder()
                .name(driverRequest.getName())
                .age(driverRequest.getAge())
                .email(driverRequest.getEmail())
                .build();
    }

    // Entity to ResponseDTO
    public static DriverResponse driverToDriverResponse(Driver savedDriver){

        return DriverResponse.builder()
                .driverId(savedDriver.getDriverId())
                .name(savedDriver.getName())
                .age(savedDriver.getAge())
                .email(savedDriver.getEmail())
                .build();
    }
}
