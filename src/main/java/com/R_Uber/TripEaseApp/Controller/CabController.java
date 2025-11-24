package com.R_Uber.TripEaseApp.Controller;

import com.R_Uber.TripEaseApp.DTO.request.CabRequest;
import com.R_Uber.TripEaseApp.DTO.response.CabResponse;
import com.R_Uber.TripEaseApp.Service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cab")
public class CabController {

    @Autowired
    CabService cabService;

    @PostMapping("/register/driver/{driverId}")
    public CabResponse registerCab(@RequestBody CabRequest cabRequest,
                                   @PathVariable("driverId") int driverId){
        return cabService.registerCab(cabRequest,driverId);
    }
}
