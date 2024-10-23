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
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseEntity<PayingGuest> getPayingGuest(Long id) {
        //TODO: Implement this method & return the details about a PG
        // Step 1 - Check the payingGuestId if not exist - 404 NOT FOUND
        // Step 2 - Get the details of the PG weather visibility is public or not - 403 FORBIDDEN
        // Step 3 - Return the details
        log.info("Get Paying Guest by Id triggered. Id : {} ", id);
        if(id==null) throw new IllegalArgumentException("id cannot be null");
        PayingGuest pagingGuest = payingGuestRepository.findPayingGuestByPayingGuestId(id);
        if(pagingGuest==null) throw new NullPointerException("pagingGuest cannot be null");
        return ResponseEntity.ok(pagingGuest);
    }

    public ResponseEntity<?> listNPayingGuest(int page,int size) {
        //TODO: Implement this method & return the list of PGs within the given distance
        // Step 1 - N must be between 25 and 100
        // Step 2 - Get the details of the PG weather visibility is public or not
        // Step 3 - Return the list
        log.info("List N Paying Guest triggered. Page : {} Size : {} ", page, size);
        if(size<25 || size>100) throw new IllegalArgumentException("size must be between 25 and 100 records");
        if(page<1) throw new IllegalArgumentException("Illegal page value provided");
        Pageable pageable = PageRequest.of(page, size);
        List<PayingGuest> listOfPayingGuestWithPagination = payingGuestRepository.findAll(pageable).stream().toList();
        return ResponseEntity.ok(listOfPayingGuestWithPagination);
    }
}
