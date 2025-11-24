package com.R_Uber.TripEaseApp.Service;

import com.R_Uber.TripEaseApp.DTO.request.BookingRequest;
import com.R_Uber.TripEaseApp.DTO.response.BookingResponse;
import com.R_Uber.TripEaseApp.Model.Booking;
import com.R_Uber.TripEaseApp.Model.Cab;
import com.R_Uber.TripEaseApp.Model.Customer;
import com.R_Uber.TripEaseApp.Model.Driver;
import com.R_Uber.TripEaseApp.Repository.BookingRepository;
import com.R_Uber.TripEaseApp.Repository.CabRepository;
import com.R_Uber.TripEaseApp.Repository.CustomerRepository;
import com.R_Uber.TripEaseApp.Repository.DriverRepository;
import com.R_Uber.TripEaseApp.exception.CabUnavailableException;
import com.R_Uber.TripEaseApp.exception.CustomerNotFoundException;
import com.R_Uber.TripEaseApp.transformer.BookingTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CabRepository cabRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    DriverRepository driverRepository;

    public BookingResponse bookCab(BookingRequest bookingRequest, int customerid) {

        // validating if customer exist or not
        Optional<Customer> optionalCustomer = customerRepository.findById(customerid);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("INVALID CUSTOMER ID");
        }
        Customer customer = optionalCustomer.get();


        // checking if cab available or not
        Cab availableCab = cabRepository.getAvailableCabRandomly();
        if(availableCab==null){
            throw new CabUnavailableException("NOO!! CAB AVAILABLE");
        }

        // DTO to entity
        Booking booking = BookingTransformer.bookingRequestTOBooking(bookingRequest,availableCab.getPerKmRate());
        Booking savedBooking = bookingRepository.save(booking);
        availableCab.setAvailable(false);
        customer.getBookings().add(savedBooking);

        Driver driver = driverRepository.getDriverById(availableCab.getCabId());
        driver.getBookings().add(savedBooking);

        Customer savedcustomer = customerRepository.save(customer);
        Driver savedDriver = driverRepository.save(driver);


        return BookingTransformer.bookingToBookingResponse(savedBooking, savedcustomer, availableCab, savedDriver);

    }
}
