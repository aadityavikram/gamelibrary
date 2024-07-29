package com.gamelibrary.gamelibrary.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CombinedResponse {
    private FormattedProfileResponse profile;
    private List<GamesHome> games;
}
