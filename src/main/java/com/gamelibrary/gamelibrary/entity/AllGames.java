package com.gamelibrary.gamelibrary.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AllGames {

  @JsonProperty(value="applist")
  private GamesList appList;
}

