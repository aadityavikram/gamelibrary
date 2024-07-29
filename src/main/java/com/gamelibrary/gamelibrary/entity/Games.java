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
public class Games {

  @JsonProperty(value="appid")
  private String appId;
  private String name;
  @JsonProperty(value="playtime_forever")
  private String playtimeForever;
  @JsonProperty(value="img_icon_url")
  private String imgIconUrl;
  @JsonProperty(value="playtime_windows_forever")
  private String playtimeWindowsForever;
  @JsonProperty(value="playtime_mac_forever")
  private String playtimeMacForever;
  @JsonProperty(value="playtime_linux_forever")
  private String playtimeLinuxForever;
  @JsonProperty(value="playtime_deck_forever")
  private String playtimeDeckForever;
  @JsonProperty(value="rtime_last_played")
  private String rTimeLastPlayed;
  @JsonProperty(value="playtime_disconnected")
  private String playtimeDisconnected;
}

