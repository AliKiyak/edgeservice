package be.thomasmore.team14.edge.controllers;

import be.thomasmore.team14.edge.models.Game;
import be.thomasmore.team14.edge.models.GenericResponseWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
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

    @GetMapping("/games/filter/{title}")
    public List<Game> getFilteredGames(@PathVariable("title") String title) {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://game-service/games/search/findGameByTitleContainingIgnoreCase?title=" + title , GenericResponseWrapper.class
        );

        List<Game> games = objectMapper.convertValue(wrapper.get_embedded().get("games"), new TypeReference<List<Game>>() {
        });

        return games;
    }

    @GetMapping("/detail/{gameid}")
    public Game getGame(@PathVariable("gameid") String gameId) {

        Game game = restTemplate.getForObject(
                "http://game-service/games/search/findGameById?id=" + gameId, Game.class);


        return game;
    }



    @PostMapping("/addgame")
    public ResponseEntity<String> postGame(@RequestBody Game game) {
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
        ResponseEntity<Game> result = restTemplate.postForEntity(
                "http://game-service/games/", game, Game.class
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletegame/{gameid}")
    public ResponseEntity deleteGameAndAssociations(@PathVariable("gameid") String gameid) {

        try {
            restTemplate.delete("http://team-service/teams/search/deleteTeamsByGameId?gameid" + gameid);

        } catch (final HttpClientErrorException e){
            System.out.println(e);
        }

        try {
            restTemplate.delete("http://tournament-service/tournaments/search/deleteTournamentsByGameId?gameid" + gameid);
        } catch (final HttpClientErrorException e){
            System.out.println(e);
        }
        restTemplate.delete("http://game-service/games/" + gameid);
        return ResponseEntity.ok().build();
    }

    @PutMapping("editgame/{gameid}")
    public ResponseEntity<String> editgame (@PathVariable("gameid") String gameId,
                                            @RequestBody Game game) {
        restTemplate.put("http://game-service/games/" + gameId, game, Game.class);

        return ResponseEntity.ok().build();
    }
}
