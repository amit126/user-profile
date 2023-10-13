package com.tcg.service;

import com.tcg.dto.UserProfileDTO;
import org.springframework.http.ResponseEntity;

public interface UserProfileService {

    ResponseEntity getUserProfile();

    ResponseEntity addUserProfile(UserProfileDTO userProfileDTO);
}
