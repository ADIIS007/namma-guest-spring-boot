package com.project.namma_guest.service;

import com.project.namma_guest.data.Sharing;
import com.project.namma_guest.model.PayingGuest;
import com.project.namma_guest.repository.PayingGuestRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PayingGuestService {
    private final PayingGuestRepository payingGuestRepository;
    @Autowired
    public PayingGuestService(PayingGuestRepository payingGuestRepository) {
        this.payingGuestRepository = payingGuestRepository;
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
}
