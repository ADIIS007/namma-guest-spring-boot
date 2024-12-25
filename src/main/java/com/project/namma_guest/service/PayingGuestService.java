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
    public Long setUpHostel(String email,Hostel hostel) {
        if(!Utilities.isValidEmail(email)) throw new IllegalArgumentException("Invalid email.");
        if(!Utilities.isValidHostel(hostel)) throw new IllegalArgumentException("Invalid Hostel Data.");
        Users owner = usersRepository.findUsersByEmail(email);
        if (owner!=null) {
            if(owner.getOwnsPayingGuest() != null) {
                throw new IllegalStateException("User already has a hostel.");
            } else if(owner.getUserType() == null || !owner.getUserType().equalsIgnoreCase("owner")) {
                throw new IllegalStateException("User is not a owner.");
            } else {
                PayingGuest payingGuest = new PayingGuest();
                payingGuest = payingGuestRepository.save(payingGuest);
                owner.setOwnsPayingGuest(payingGuest);

                //Updating Hostel information
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

                usersRepository.save(owner);
                payingGuestRepository.save(payingGuest);
                return payingGuest.getPayingGuestId();
            }
        } else {
            throw new IllegalArgumentException("Owner not found.");
        }
    }

    @Transactional
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

    @Transactional
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
