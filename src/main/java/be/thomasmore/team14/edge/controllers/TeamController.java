package be.thomasmore.team14.edge.controllers;

import be.thomasmore.team14.edge.models.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/teams")
    public List<TeamWithGame> getTeams() {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://team-service/teams", GenericResponseWrapper.class
        );

        List<Team> teams = objectMapper.convertValue(wrapper.get_embedded().get("teams"), new TypeReference<List<Team>>() { });
        List<TeamWithGame> returnList = new ArrayList<>();

        for (Team team : teams) {
            Game game = restTemplate.getForObject("http://game-service/games/" + team.getGameId(), Game.class);
            returnList.add(new TeamWithGame(team, game.getTitle()));
        }
        return returnList;
    }

    @GetMapping("/game/{gameid}")
    public List<TeamWithGame> getTeamFromCertainGame(@PathVariable("gameid") String gameId) {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://team-service/teams/search/findTeamByGameId?gameid=" + gameId, GenericResponseWrapper.class
        );

        List<Team> teams = objectMapper.convertValue(wrapper.get_embedded().get("teams"), new TypeReference<List<Team>>() { });
        List<TeamWithGame> returnList = new ArrayList<>();

        for (Team team : teams) {
            Game game = restTemplate.getForObject("http://game-service/games/" + team.getGameId(), Game.class);
            returnList.add(new TeamWithGame(team, game.getTitle()));
        }
        return returnList;
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable("id") String id) {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://team-service/teams/search/findTeamById?id=" + id, GenericResponseWrapper.class);

        List<Team> team = objectMapper.convertValue(wrapper.get_embedded().get("teams"), new TypeReference<List<Team>>() {
        });

        return team.get(0);
    }


}
