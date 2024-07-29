package com.gamelibrary.gamelibrary.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendsResponseV2 {
    @JsonProperty(value = "steamid")
    private String steamId;
    private String relationship;
    @JsonProperty(value = "friend_since")
    private String friendSince;
}
