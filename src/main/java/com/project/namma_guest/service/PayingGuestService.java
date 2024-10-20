package com.project.namma_guest.service;

import com.project.namma_guest.DTO.Request.Hostel;
import com.project.namma_guest.helper.Utilities;
import com.project.namma_guest.model.PayingGuest;
import com.project.namma_guest.model.Users;
import com.project.namma_guest.repository.PayingGuestRepository;
import com.project.namma_guest.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayingGuestService {
    private final PayingGuestRepository payingGuestRepository;
    private final UsersRepository usersRepository;
    @Autowired
    public PayingGuestService(PayingGuestRepository payingGuestRepository,UsersRepository usersRepository) {
        this.payingGuestRepository = payingGuestRepository;
        this.usersRepository = usersRepository;
    }

    @Transactional
    public Long setUpHostel(String email) {
        if(!Utilities.isValidEmail(email)) throw new IllegalArgumentException("Invalid email");
        Users owner = usersRepository.findUsersByEmail(email);
        if (owner!=null) {
            if(owner.getOwnsPayingGuest() != null) {
                throw new IllegalStateException("User already has a hostel");
            } else {
                PayingGuest payingGuest = new PayingGuest();
                payingGuest = payingGuestRepository.save(payingGuest);
                owner.setOwnsPayingGuest(payingGuest);
                usersRepository.save(owner);
                return payingGuest.getPayingGuestId();
            }
        } else {
            throw new IllegalArgumentException("Owner not found");
        }
    }

    @Transactional
    public ResponseEntity<?> updateHostel(Hostel hostel, String email) {
        //TODO: Sets all the data into the hostel
        // Step 0 - Validate the Incoming data weather null or no make a function in helper folder & validation Class - Unprocessable Entity 422
        // Step 1 - Validate the request (input validation, data format etc.) - 400 Bad Request
        // Step 2 - Get the UserId & HostelId & Check weather the person who is trying to update is the owner only - 403 Forbidden
        // Step 3 - Update the hostel details (only update allowed fields) - 403 Forbidden
        // Step 4 - Return the details of the hostel if updated - 200 OK
        if(!Utilities.isValidEmail(email)){
            throw new IllegalArgumentException("Invalid Email");
        }
        if(!Utilities.isValidHostel(hostel)){
            throw new IllegalArgumentException("Invalid Hostel Data");
        }
        Users user = usersRepository.findUsersByEmail(email);
        if(user == null) {
            throw new IllegalArgumentException("Owner not found");
        }
        if (user.getOwnsPayingGuest() == null) {
            throw new IllegalArgumentException("Paying guest not found for the user");
        }
        PayingGuest payingGuest = user.getOwnsPayingGuest();
        payingGuest.setName(hostel.getName());
        payingGuest.setAddress(hostel.getAddress());
        payingGuest.setCountry(hostel.getCountry());
        payingGuest.setState(hostel.getState());
        payingGuest.setCity(hostel.getCity());
        payingGuest.setContactNumber(hostel.getContactNumber());
        payingGuest.setEmail(hostel.getEmail());
        payingGuest.setWhatsappNumber(hostel.getWhatsappNumber());

        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(hostel.getLocation().getX(), hostel.getLocation().getY()));
        payingGuest.setLocation(point);
        payingGuestRepository.save(payingGuest);
        return ResponseEntity.ok(payingGuest);
    }
}
