package com.gamelibrary.gamelibrary.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FriendsResponse {
    private String steamId;
    private String name;
    private String friendSince;
}
