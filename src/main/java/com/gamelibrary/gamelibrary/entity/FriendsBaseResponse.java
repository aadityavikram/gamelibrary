package com.gamelibrary.gamelibrary.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FriendsBaseResponse {
    @JsonProperty(value = "friendslist")
    private FriendsResponseList friendsList;
}
