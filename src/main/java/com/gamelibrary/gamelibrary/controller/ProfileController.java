package com.gamelibrary.gamelibrary.controller;

import com.gamelibrary.gamelibrary.entity.FormattedProfileResponse;
import com.gamelibrary.gamelibrary.entity.GamesHome;
import com.gamelibrary.gamelibrary.entity.GamesListResponse;
import com.gamelibrary.gamelibrary.entity.ProfileBaseResponse;
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
public class ProfileController {

    @Autowired
    private GameLibraryService gameLibraryService;

    @GetMapping("/api/v1/profile")
    public ModelAndView fetchProfile(
            @Parameter(name = "steamid", description = "Steam ID of user", in = ParameterIn.QUERY) @Valid @RequestParam(value = "steamid", required = false) String steamId
    ) {
        ProfileBaseResponse rawProfileResponse = gameLibraryService.getProfileInfo(steamId);
        FormattedProfileResponse profile = gameLibraryService.getFormattedProfileResponse(rawProfileResponse);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("profile", profile);
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @GetMapping("/api/v2/profile")
    public ResponseEntity<FormattedProfileResponse> getPlayerProfile(
            @Parameter(name = "steamid", description = "Steam ID of user", in = ParameterIn.QUERY) @Valid @RequestParam(value = "steamid", required = false) String steamId
    ) {
        ProfileBaseResponse rawProfileResponse = gameLibraryService.getProfileInfo(steamId);
        FormattedProfileResponse formattedProfileResponse = gameLibraryService.getFormattedProfileResponse(rawProfileResponse);
        return new ResponseEntity<>(formattedProfileResponse, HttpStatus.OK);
    }
}
