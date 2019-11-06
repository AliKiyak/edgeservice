package be.thomasmore.team14.edge.controllers;

import be.thomasmore.team14.edge.models.GenericResponseWrapper;
import be.thomasmore.team14.edge.models.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.GenericSignatureFormatError;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/players")
    public List<Player> getPlayers() {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://player-service/players", GenericResponseWrapper.class);

        List<Player> players = objectMapper.convertValue(wrapper.get_embedded().get("players"), new TypeReference<List<Player>>() {});

        return players;
    }

    @GetMapping("player/{gamerTag}")
    public Player getPlayerByGamerTag(@PathVariable("gamerTag") String gamerTag) {
        Player player = restTemplate.getForObject(
                "http://player-service/players/search/findPlayerByGamerTag?gamerTag=" + gamerTag, Player.class);

        return player;
    }

    @GetMapping("detail/{id}")
    public Player getPlayerById(@PathVariable("id") String id) {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://player-service/players/search/findPlayerById?id=" + id, GenericResponseWrapper.class);

        List<Player> player = objectMapper.convertValue(wrapper.get_embedded().get("players"), new TypeReference<List<Player>>() {
        });

        return player.get(0);
    }

//    @PostMapping("/addplayer")
}
