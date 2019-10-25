package be.thomasmore.team14.edge.controllers;

import be.thomasmore.team14.edge.models.Game;
import be.thomasmore.team14.edge.models.GenericResponseWrapper;
import be.thomasmore.team14.edge.models.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
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


}
