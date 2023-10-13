package com.tcg.controller;

import com.tcg.dto.TaskDTO;
import com.tcg.dto.UserProfileDTO;
import com.tcg.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/enterprise/profile")
    public ResponseEntity getUserProfile() {
        log.info("getUserProfile starts...");

        return userProfileService.getUserProfile();
    }

    @PostMapping("/enterprise/profile")
    public ResponseEntity addUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
        log.info("addUserProfile starts...");

        return userProfileService.addUserProfile(userProfileDTO);
    }

}
