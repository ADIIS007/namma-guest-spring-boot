package com.project.namma_guest.service;

import com.project.namma_guest.data.Sharing;
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
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<PayingGuest> getAllPayingGuest() {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordinate = new Coordinate(1.0, 2.0);
//        PayingGuest payingGuest = new PayingGuest(1L,"Test PG", "Test Address", "Test City", "Test State", "Test Country", "Test Contact Number", "Test Email", 1000, 1000, Sharing.ONE, geometryFactory.createPoint(coordinate));
//        payingGuestRepository.save(payingGuest);
        List<PayingGuest> guest = payingGuestRepository.findAll();
        return guest;
    }

    public Boolean existsByEmail(String email) {
        return payingGuestRepository.existsByEmail(email);
    }

    @Transactional
    public Long setUpHostel(Long ownerId) {
        if(ownerId==null) throw new IllegalArgumentException("Improper Data Present");
        Optional<Users> owner = usersRepository.findByUserUniqueId(ownerId);
        if (owner.isPresent()) {
            Users user = owner.get();
            if(user.getOwnsPayingGuest() != null) {
                throw new IllegalStateException("User already has a hostel");
            } else {
                PayingGuest payingGuest = new PayingGuest();
                payingGuest = payingGuestRepository.save(payingGuest);
                user.setOwnsPayingGuest(payingGuest);
                usersRepository.save(user);
                return payingGuest.getPayingGuestId();
            }
        } else {
            throw new IllegalArgumentException("Owner not found");
        }
    }
}
