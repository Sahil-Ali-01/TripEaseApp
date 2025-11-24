package com.R_Uber.TripEaseApp.Controller;

import com.R_Uber.TripEaseApp.DTO.request.BookingRequest;
import com.R_Uber.TripEaseApp.DTO.response.BookingResponse;
import com.R_Uber.TripEaseApp.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/book/customer/{customerId}")
    public BookingResponse bookCab(@RequestBody BookingRequest bookingRequest,
                                   @PathVariable("customerId") int customerid){
        return bookingService.bookCab(bookingRequest,customerid);
    }

}
