package com.R_Uber.TripEaseApp.Controller;

import com.R_Uber.TripEaseApp.DTO.request.DriverRequest;
import com.R_Uber.TripEaseApp.DTO.response.DriverResponse;
import com.R_Uber.TripEaseApp.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping("/add")
    public DriverResponse addDriver(@RequestBody DriverRequest driverRequest){
        return driverService.addDriver(driverRequest);
    }
}

