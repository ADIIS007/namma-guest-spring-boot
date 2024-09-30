package com.project.namma_guest.repository;

import com.project.namma_guest.model.facility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FacilityRepositoryTest {

    @Autowired
    private FacilityRepository facilityRepository;

    @BeforeEach
    public void saveFacility() {
        facility fac1= new facility();
        fac1.setFacilityName("Facility 1");
        fac1.setFacilityDescription("Facility Description");
        fac1.setFacilityId(1L);
        facilityRepository.save(fac1);
    }

    @Test
    public void testfindById() {
        Optional<facility> fac1= facilityRepository.findById(1L);
        assertTrue(fac1.isPresent());
    }

    @Test
    public void testfindByFacilityName() {
        Optional<facility> fac1 = facilityRepository.findById(1L);
        assertTrue(fac1.isPresent());
        assertEquals("Facility 1", fac1.get().getFacilityName());
    }

    @Test
    public void testfindByFacilityDescription() {
        Optional<facility> fac1 = facilityRepository.findById(1L);
        assertTrue(fac1.isPresent());
        assertEquals("Facility Description", fac1.get().getFacilityDescription());
    }

    @Test
    public void testUpdateFacilityName(){
        Optional<facility> fac1= facilityRepository.findById(1L);
        assertTrue(fac1.isPresent());
        fac1.get().setFacilityName("New Facility Name");
        facilityRepository.save(fac1.get());
    }

    @Test
    public void testUpdateFacilityDescription(){
        Optional<facility> fac1= facilityRepository.findById(1L);
        assertTrue(fac1.isPresent());
        fac1.get().setFacilityDescription("New Facility Description");
        facilityRepository.save(fac1.get());
    }
    @Test
     public void testDeleteFacilityName(){
        Optional<facility> fac1= facilityRepository.findById(1L);
        assertTrue(fac1.isPresent());
        facilityRepository.delete(fac1.get());
        Optional<facility> fac2= facilityRepository.findById(1L);
        assertFalse(fac2.isPresent());
    }
}