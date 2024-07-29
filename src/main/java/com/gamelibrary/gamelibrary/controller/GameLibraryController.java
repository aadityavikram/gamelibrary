package com.gamelibrary.gamelibrary.controller;

import com.gamelibrary.gamelibrary.entity.GamesHome;
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
public class GameLibraryController {

    @Autowired
    private GameLibraryService gameLibraryService;

    @GetMapping("/api/v1/games")
    public ModelAndView fetchAllGames(
            @Parameter(name = "steamid", description = "Steam ID of user", in = ParameterIn.QUERY) @Valid @RequestParam(value = "steamid", required = false) String steamId
    ) {
//        76561198439305330
//        76561198984434222
//        76561199079539335
//        76561198026306582
        GamesListResponse listOfOwnedGames = gameLibraryService.listGames(steamId);
        List<GamesHome> games = gameLibraryService.getListOfFormattedGames(listOfOwnedGames);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("games", games);
        modelAndView.setViewName("game");
        return modelAndView;
    }

    @GetMapping("/api/v2/games")
    public ResponseEntity<FormattedGamesResponse> listGames(
            @Parameter(name = "steamid", description = "Steam ID of user", in = ParameterIn.QUERY) @Valid @RequestParam(value = "steamid", required = false) String steamId
    ) {
        GamesListResponse listOfOwnedGames = gameLibraryService.listGames(steamId);
        FormattedGamesResponse finalResponse = gameLibraryService.getFormattedGamesResponse(listOfOwnedGames);
        return new ResponseEntity<>(finalResponse, HttpStatus.OK);
    }
}
