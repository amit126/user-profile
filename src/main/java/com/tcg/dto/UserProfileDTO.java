package com.tcg.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfileDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String externalId;
    private String company;
    private String pictureId;
    private String fullname;
    private String password;
    private String type;
    private String status;
    private String created;
    private String lastUpdate;
    private int tenantId;
    private String latestSyncTimeStamp;
    private String capabilities;
    private List<String> apps;
    private String primaryGroup;
    private String tenantPictureId;
    private String tenantName;
}
