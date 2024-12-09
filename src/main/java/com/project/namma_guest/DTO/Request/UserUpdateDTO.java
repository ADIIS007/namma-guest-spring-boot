package com.project.namma_guest.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {

    private String given_name;
    private String family_name;
    private String phone_number;

}
