package com.gamelibrary.gamelibrary.controller;

import com.gamelibrary.gamelibrary.entity.*;
import com.gamelibrary.gamelibrary.service.GameLibraryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InfoController {

    @Autowired
    private GameLibraryService gameLibraryService;

    @GetMapping("/api/v1/info")
    public ModelAndView fetchAllInfo(
            @Parameter(name = "steamid", description = "Steam ID of user", in = ParameterIn.QUERY) @Valid @RequestParam(value = "steamid", required = false) String steamId
    ) {
//        76561198439305330
        GamesListResponse listOfOwnedGames = gameLibraryService.listGames(steamId);
        List<GamesHome> games = gameLibraryService.getListOfFormattedGames(listOfOwnedGames);
        ProfileBaseResponse rawProfileResponse = gameLibraryService.getProfileInfo(steamId);
        FormattedProfileResponse profile = gameLibraryService.getFormattedProfileResponse(rawProfileResponse);
        CombinedResponse combinedResponse = new CombinedResponse(profile, games);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("response", combinedResponse);
        modelAndView.setViewName("info");
        return modelAndView;
    }

    @GetMapping("/api/v2/info")
    public ResponseEntity<CombinedResponseV2> getAllInfo(
            @Parameter(name = "steamid", description = "Steam ID of user", in = ParameterIn.QUERY) @Valid @RequestParam(value = "steamid", required = false) String steamId
    ) {
        GamesListResponse listOfOwnedGames = gameLibraryService.listGames(steamId);
        FormattedGamesResponse finalResponse = gameLibraryService.getFormattedGamesResponse(listOfOwnedGames);
        ProfileBaseResponse rawProfileResponse = gameLibraryService.getProfileInfo(steamId);
        FormattedProfileResponse formattedProfileResponse = gameLibraryService.getFormattedProfileResponse(rawProfileResponse);
        CombinedResponseV2 combinedResponse = new CombinedResponseV2(formattedProfileResponse, finalResponse);
        return new ResponseEntity<>(combinedResponse, HttpStatus.OK);
    }
}
