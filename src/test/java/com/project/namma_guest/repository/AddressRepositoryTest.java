package com.project.namma_guest.repository;

import com.project.namma_guest.model.address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    public void testFullAddress() {
        address address = new address();
        address.setAddressId(1L);
        address.setAddressLine1("123 Main St");
        address.setAddressLine2("Apt 4B");
        address.setCountry(Locale.US);
        address.setCity("New York");
        address.setPostalCode("10001");
        addressRepository.saveAndFlush(address);
    }

    @Test
    public void testAddressId(){
        Optional<address> address1 = addressRepository.findById(1L);
        assertTrue(address1.isPresent());
    }
    @Test
    public void testAddressLine1() {
        Optional<address> address1 = addressRepository.findById(1L);
        assertTrue(address1.isPresent(), "Address should be present");
        assertEquals("123 Main St", address1.get().getAddressLine1(), "Address line 1 should match");
    }
    @Test
    public void testAddressLine2() {
        Optional<address> address1 = addressRepository.findById(1L);
        assertTrue(address1.isPresent(), "Address should be present");
        assertEquals("Apt 4B", address1.get().getAddressLine2(), "Address line 2 should match");
    }
    @Test
    public void updateCountry() {
        Optional<address> address1 = addressRepository.findById(1L);
        assertTrue(address1.isPresent(), "Address should be present");
        if (address1.get().getCountry().equals(Locale.US)) {
            address1.get().setCountry(Locale.UK);
            addressRepository.save(address1.get());
        }
        Optional<address> updatedAddress = addressRepository.findById(1L);
        assertTrue(updatedAddress.isPresent(), "Updated address should be present");
        assertEquals(Locale.UK, updatedAddress.get().getCountry(), "Country should be updated to Locale.UK");
    }
    @Test
    public void deleteCity(){
        Optional<address> address1 = addressRepository.findById(1L);
        assertTrue(address1.isPresent(), "Address should be present");
        addressRepository.delete(address1.get());
        Optional<address> deletedAddress = addressRepository.findById(1L);
        assertFalse(deletedAddress.isPresent(), "Address should be deleted and not present");
    }
}