package com.gamelibrary.gamelibrary.service;

import com.gamelibrary.gamelibrary.entity.*;

import java.util.List;

public interface GameLibraryService {

    GamesListResponse listGames(String steamId);
    AllGames listAllGamesOnSteam();
    FormattedGamesResponse getFormattedGamesResponse(GamesListResponse listOfOwnedGames);
    List<GamesHome> getListOfFormattedGames(GamesListResponse listOfOwnedGames);
    ProfileBaseResponse getProfileInfo(String steamId);
    FormattedProfileResponse getFormattedProfileResponse(ProfileBaseResponse rawProfileResponse);
    FriendsBaseResponse getFriendListInfo(String steamId);
    FormattedFriendsResponse getFormattedFriendsResponse(FriendsBaseResponse rawFriendsResponse);

}
