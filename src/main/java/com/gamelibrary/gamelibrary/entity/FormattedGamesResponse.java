package com.gamelibrary.gamelibrary.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FormattedGamesResponse {
  private String gameCountTotal;
  private String gameCountPlayed;
  private List<FormattedGames> gameList;
}

