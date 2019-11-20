package be.thomasmore.team14.edge.controllers;

import be.thomasmore.team14.edge.models.GenericResponseWrapper;
import be.thomasmore.team14.edge.models.Player;
import be.thomasmore.team14.edge.models.PlayerWithTeamAndTeammates;
import be.thomasmore.team14.edge.models.Team;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.GenericSignatureFormatError;
import java.util.ArrayList;
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
    public List<PlayerWithTeamAndTeammates> getPlayerById(@PathVariable("id") String id) {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://player-service/players/search/findPlayerById?id=" + id, GenericResponseWrapper.class);

        List<Player> players = objectMapper.convertValue(wrapper.get_embedded().get("players"), new TypeReference<List<Player>>() {
        });

        List<PlayerWithTeamAndTeammates> returnList = new ArrayList<>();

        for (Player player: players) {
            GenericResponseWrapper wrapper2 = restTemplate.getForObject(
                    "http://team-service/teams/search/findTeamById?id=" + player.getTeamId(), GenericResponseWrapper.class);
            List<Team> team = objectMapper.convertValue(wrapper2.get_embedded().get("teams"), new TypeReference<List<Team>>() {});

            GenericResponseWrapper wrapper3 = restTemplate.getForObject(
                    "http://player-service/players/search/findPlayersByTeamId?teamId=" + player.getTeamId(), GenericResponseWrapper.class);
            List<Player> teammembers = objectMapper.convertValue(wrapper3.get_embedded().get("players"), new TypeReference<List<Player>>() {});

            teammembers.remove(player);

            returnList.add(new PlayerWithTeamAndTeammates(player, team.get(0).getName(), team.get(0).getImageUrl(), teammembers));
        }

        return returnList;
    }

    @PostMapping("/addplayer")
    public ResponseEntity<String> postPlayer(@RequestBody Player player) {
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);

        ResponseEntity<Player> result = restTemplate.postForEntity(
                "http://player-service/players/", player, Player.class
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/players/filter/{gamertag}")
    public List<Player> getFilteredPlayers(@PathVariable("gamertag") String gamertag) {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://player-service/players/search/findPlayerByGamerTagContainingIgnoreCase?gamertag=" + gamertag , GenericResponseWrapper.class
        );

        List<Player> players = objectMapper.convertValue(wrapper.get_embedded().get("players"), new TypeReference<List<Player>>() {
        });

        return players;
    }

    @GetMapping("players/team/{teamid}")
    public List<Player> getPlayersOfTeam(@PathVariable("teamid") String teamid) {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://player-service/players/search/findPlayersByTeamId?teamId=" + teamid , GenericResponseWrapper.class
        );

        List<Player> players = objectMapper.convertValue(wrapper.get_embedded().get("players"), new TypeReference<List<Player>>() {
        });

        return players;
    }

    @DeleteMapping("/deleteplayer/{playerid}")
    public ResponseEntity deletePlayer(@PathVariable("playerid") String playerid) {

        restTemplate.delete("http://player-service/players/" + playerid);
        return ResponseEntity.ok().build();
    }
}
