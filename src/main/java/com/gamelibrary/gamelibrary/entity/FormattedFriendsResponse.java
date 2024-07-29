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
public class FormattedFriendsResponse {
    private String friendsCountTotal;
    private List<FriendsResponseV2> friends;
}
