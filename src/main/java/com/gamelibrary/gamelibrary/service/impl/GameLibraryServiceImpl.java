package com.gamelibrary.gamelibrary.service.impl;

import com.gamelibrary.gamelibrary.entity.GamesHome;
import com.gamelibrary.gamelibrary.entity.*;
import com.gamelibrary.gamelibrary.service.GameLibraryService;
import com.gamelibrary.gamelibrary.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GameLibraryServiceImpl implements GameLibraryService {

    @Value("${steam.api.key}")
    private String steamApiKey;

    @Value("${steam.api.response.format}")
    private String steamApiResponseFormat;

    @Value("${games.library.baseUrl}")
    private String gameLibraryBaseUrl;

    @Value("${games.baseUrl}")
    private String allGamesBaseUrl;

    @Value("${games.image.baseUrl}")
    private String allGamesImageBaseUrl;

    @Value("${games.player.summaries.baseUrl}")
    private String playerSummaryUrl;

    @Value("${game.player.friendList.baseUrl}")
    private String playerFriendListUrl;

    @Autowired
    private Util util;

    @Override
    public GamesListResponse listGames(String steamId) {
        String url = gameLibraryBaseUrl + "key=" + steamApiKey + "&steamid=" + steamId + "&format=" + steamApiResponseFormat +"&include_appinfo=true";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GamesListResponse> response
                = restTemplate.getForEntity(url, GamesListResponse.class);
        return response.getBody();
    }

    @Override
    public AllGames listAllGamesOnSteam() {
        String url = allGamesBaseUrl;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AllGames> response
                = restTemplate.getForEntity(url, AllGames.class);
        return response.getBody();
    }

    @Override
    public FormattedGamesResponse getFormattedGamesResponse(GamesListResponse listOfOwnedGames) {

        List<Games> allOwnedGames = listOfOwnedGames.getResponse().getGames();

        List<FormattedGames> finalListOfOwnedGames = new ArrayList<>();
        int countOfGamesPlayed = 0;

        for (Games games: allOwnedGames) {
            if (!games.getPlaytimeForever().equals("0")) {
                FormattedGames formattedGames = new FormattedGames();
                formattedGames.setName(games.getName());
                formattedGames.setHoursPlayed(Float.parseFloat(games.getPlaytimeForever())/60);
                finalListOfOwnedGames.add(formattedGames);
                countOfGamesPlayed += 1;
            }
        }

        finalListOfOwnedGames.sort(Comparator.comparing(FormattedGames::getHoursPlayed, Comparator.reverseOrder()));

        FormattedGamesResponse formattedGamesResponse = new FormattedGamesResponse();
        formattedGamesResponse.setGameCountTotal(listOfOwnedGames.getResponse().getGameCount());
        formattedGamesResponse.setGameCountPlayed(String.valueOf(countOfGamesPlayed));
        formattedGamesResponse.setGameList(finalListOfOwnedGames);
        return formattedGamesResponse;
    }

    @Override
    public List<GamesHome> getListOfFormattedGames(GamesListResponse listOfOwnedGames) {

        List<Games> allOwnedGames = listOfOwnedGames.getResponse().getGames();

        List<GamesHome> finalListOfOwnedGames = new ArrayList<>();

        for (Games games: allOwnedGames) {
            if (!games.getPlaytimeForever().equals("0")) {
                GamesHome gamesHome = new GamesHome();
                gamesHome.setName(games.getName());
                gamesHome.setHours(Float.parseFloat(games.getPlaytimeForever()));
                gamesHome.setImageLink(allGamesImageBaseUrl + games.getAppId() + "/header.jpg");
//                gamesHome.setLastTimePlayed(util.getDateTime(games.getRTimeLastPlayed()));
                finalListOfOwnedGames.add(gamesHome);
            }
        }

        finalListOfOwnedGames.sort(Comparator.comparing(GamesHome::getHours, Comparator.reverseOrder()));

        for (GamesHome gamesHome: finalListOfOwnedGames) {
            if (gamesHome.getHours() >= 60.0) {
                gamesHome.setHours(gamesHome.getHours()/60);
                gamesHome.setTimeUnit("hours");
            } else {
                gamesHome.setHours(gamesHome.getHours());
                gamesHome.setTimeUnit("minute");
            }
        }
        return finalListOfOwnedGames;
    }

    @Override
    public ProfileBaseResponse getProfileInfo(String steamId) {
        String url = playerSummaryUrl + "?key=" + steamApiKey + "&steamids=" + steamId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ProfileBaseResponse> response
                = restTemplate.getForEntity(url, ProfileBaseResponse.class);
        return response.getBody();
    }

    @Override
    public FormattedProfileResponse getFormattedProfileResponse(ProfileBaseResponse rawProfileResponse) {
        ProfileResponse profileResponse = rawProfileResponse.getResponse().getPlayers().get(0);
//        String formattedDate = util.getDateTime(profileResponse.getLastLogOff());
        String currentStatus = util.getCurrentStatus(profileResponse.getPersonaState());
        return new FormattedProfileResponse(
                profileResponse.getSteamId(),
                profileResponse.getPersonaName(),
                profileResponse.getProfileUrl(),
                profileResponse.getAvatarFull(),
                profileResponse.getLastLogOff(),
                profileResponse.getRealName(),
                currentStatus
        );
    }

    @Override
    public FriendsBaseResponse getFriendListInfo(String steamId) {
        String url = playerFriendListUrl + "?key=" + steamApiKey + "&steamid=" + steamId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FriendsBaseResponse> response
                = restTemplate.getForEntity(url, FriendsBaseResponse.class);
        return response.getBody();
    }

    @Override
    public FormattedFriendsResponse getFormattedFriendsResponse(FriendsBaseResponse rawFriendsResponse) {
        FriendsResponseList friendsResponse = rawFriendsResponse.getFriendsList();
        List<FriendsResponseV2> friendsResponseV2s = new ArrayList<>(friendsResponse.getFriends());
        FormattedFriendsResponse formattedFriendsResponse = new FormattedFriendsResponse();
        formattedFriendsResponse.setFriendsCountTotal(String.valueOf(friendsResponse.getFriends().size()));
        formattedFriendsResponse.setFriends(friendsResponseV2s);
        return formattedFriendsResponse;
    }
}
