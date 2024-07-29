package com.gamelibrary.gamelibrary.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CombinedResponseV2 {
    private FormattedProfileResponse profile;
    private FormattedGamesResponse games;
}
