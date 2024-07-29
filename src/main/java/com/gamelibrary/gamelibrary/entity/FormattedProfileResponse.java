package com.gamelibrary.gamelibrary.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FormattedProfileResponse {
    private String steamId;
    private String personaName;
    private String profileUrl;
    private String avatar;
    private String lastLogOff;
    private String realName;
    private String currentStatus;
}
