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
public class ProfileResponse {

    @JsonProperty(value="steamid")
    private String steamId;
    @JsonProperty(value="communityvisibilitystate")
    private String communityVisibilityState;
    @JsonProperty(value="profilestate")
    private String profileState;
    @JsonProperty(value="personaname")
    private String personaName;
    @JsonProperty(value="profileurl")
    private String profileUrl;
    private String avatar;
    @JsonProperty(value="avatarmedium")
    private String avatarMedium;
    @JsonProperty(value="avatarfull")
    private String avatarFull;
    @JsonProperty(value="avatarhash")
    private String avatarHash;
    @JsonProperty(value="lastlogoff")
    private String lastLogOff;
    @JsonProperty(value="personastate")
    private String personaState;
    @JsonProperty(value="realname")
    private String realName;
    @JsonProperty(value="primaryclanid")
    private String primaryClanId;
    @JsonProperty(value="timecreated")
    private String timeCreated;
    @JsonProperty(value="personastateflags")
    private String personaStateFlags;
}
