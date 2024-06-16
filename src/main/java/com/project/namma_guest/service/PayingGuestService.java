package com.project.namma_guest.service;

import com.project.namma_guest.data.Sharing;
import com.project.namma_guest.model.PayingGuest;
import com.project.namma_guest.repository.PayingGuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PayingGuestService {
    private final PayingGuestRepository payingGuestRepository;
    @Autowired
    public PayingGuestService(PayingGuestRepository payingGuestRepository) {
        this.payingGuestRepository = payingGuestRepository;
    }
    public List<PayingGuest> getAllPayingGuest() {
        PayingGuest payingGuest = new PayingGuest(1L,"Test PG", "Test Address", "Test City", "Test State", "Test Country", "Test Contact Number", "Test Email", 1000, 1000, Sharing.ONE, 12.345678, 23.456789);
        payingGuestRepository.save(payingGuest);
        return payingGuestRepository.findAll();
    }
}
