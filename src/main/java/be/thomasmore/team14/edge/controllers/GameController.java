package be.thomasmore.team14.edge.controllers;

import be.thomasmore.team14.edge.models.Game;
import be.thomasmore.team14.edge.models.GenericResponseWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/games")
    public List<Game> getGames() {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://game-service/games", GenericResponseWrapper.class
        );

        List<Game> games = objectMapper.convertValue(wrapper.get_embedded().get("games"), new TypeReference<List<Game>>() {
        });

        return games;
    }

    @GetMapping("/detail/{gameid}")
    public Game getGame(@PathVariable("gameid") String gameId) {

        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://game-service/games/search/findGameById?id=" + gameId, GenericResponseWrapper.class);

        List<Game> games = objectMapper.convertValue(wrapper.get_embedded().get("games"), new TypeReference<List<Game>>() {});

        return games.get(0);
    }
}
