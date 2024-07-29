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

@RestController
public class FriendsController {

    @Autowired
    private GameLibraryService gameLibraryService;

    @GetMapping("/api/v1/friends")
    public ModelAndView fetchAllGames(
            @Parameter(name = "steamid", description = "Steam ID of user", in = ParameterIn.QUERY) @Valid @RequestParam(value = "steamid", required = false) String steamId
    ) {
        FriendsBaseResponse friendsBaseResponse = gameLibraryService.getFriendListInfo(steamId);
        FormattedFriendsResponse friends = gameLibraryService.getFormattedFriendsResponse(friendsBaseResponse);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("friends", friends);
        modelAndView.setViewName("friend");
        return modelAndView;
    }

    @GetMapping("/api/v2/friends")
    public ResponseEntity<FormattedFriendsResponse> listGames(
            @Parameter(name = "steamid", description = "Steam ID of user", in = ParameterIn.QUERY) @Valid @RequestParam(value = "steamid", required = false) String steamId
    ) {
        FriendsBaseResponse friendsBaseResponse = gameLibraryService.getFriendListInfo(steamId);
        FormattedFriendsResponse finalResponse = gameLibraryService.getFormattedFriendsResponse(friendsBaseResponse);
        return new ResponseEntity<>(finalResponse, HttpStatus.OK);
    }
}
