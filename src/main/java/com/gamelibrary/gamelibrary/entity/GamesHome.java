package com.gamelibrary.gamelibrary.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GamesHome {
    private String name;
    private float hours;
    private String imageLink;
    private String description;
    private String timeUnit;
    private String lastTimePlayed;
}
