package com.project.namma_guest.service;

import com.project.namma_guest.model.Users;
import com.project.namma_guest.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UsersRepository usersRepository;
    @Autowired
    public UserService(UsersRepository userRepository) {
        this.usersRepository = userRepository;
    }
    public Boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }
    public Users createNewUser(String email, String otp) {
        Users user = new Users();
        user.setOTP(otp);
        user.setEmail(email);
        user.setOTPGeneratedAt(new java.util.Date());
        return usersRepository.save(user);
    }
    public Users updateOTP(String email, String otp) {
        Users user = usersRepository.findByEmail(email);
        user.setOTP(otp);
        user.setOTPGeneratedAt(new java.util.Date());
        return usersRepository.save(user);
    }
    public Users verifyOTP(String email, String otp) {
        Users user = usersRepository.findByEmail(email);
        if(user.getOTP().equals(otp)) {
            Long temp = user.getOTPGeneratedAt().getTime() + 10 * 60 * 1000;
            if (user.getOTPGeneratedAt().getTime() + 10 * 60 * 1000 > System.currentTimeMillis()) {
                user.setIsVerified(true);
                return usersRepository.save(user);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
