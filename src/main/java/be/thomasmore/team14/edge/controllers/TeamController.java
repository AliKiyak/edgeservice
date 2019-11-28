package be.thomasmore.team14.edge.controllers;

import be.thomasmore.team14.edge.models.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value="Team controller", description="Actions that involve the Team entity")
public class TeamController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation(value = "Returns a list of all the teams", response = List.class)
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

    @ApiOperation(value = "Returns teams from a certain game", response = List.class)
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

    @ApiOperation(value = "Returns a team using its teamid", response = List.class)
    @GetMapping("/detail/{teamid}")
    public TeamWithGame getTeam(@PathVariable("teamid") String teamId) {

        Team team = restTemplate.getForObject(
                "http://team-service/teams/search/findTeamById?id=" + teamId, Team.class);
        Game game = restTemplate.getForObject("http://game-service/games/" + team.getGameId(), Game.class);

        TeamWithGame returnteam = new TeamWithGame(team, game.getTitle());
        return returnteam;
    }

    @ApiOperation(value = "Adds a team to the database", response = List.class)
    @PostMapping("/addteam")
    public ResponseEntity<String> postGame(@RequestBody Team team){
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
        ResponseEntity<Team> result = restTemplate.postForEntity(
                "http://team-service/teams/", team, Team.class
        );

        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "Deletes a team from the database", response = List.class)
    @DeleteMapping("/delete/{teamid}")
    public ResponseEntity deleteTeam(@PathVariable("teamid") String teamid) {
        restTemplate.delete("http://team-service/teams/" + teamid);

        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "Returns a team using its teamid", response = List.class)
    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable("id") String id) {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://team-service/teams/search/findTeamById?id=" + id, GenericResponseWrapper.class);

        List<Team> team = objectMapper.convertValue(wrapper.get_embedded().get("teams"), new TypeReference<List<Team>>() {
        });

        return team.get(0);
    }


    @ApiOperation(value = "Updates a team in the database using its id", response = List.class)
    @PutMapping("editteam/{teamid}")
    public ResponseEntity<String> editTeam (@PathVariable("teamid") String teamid,
                                            @RequestBody Team team) {
        restTemplate.put("http://team-service/teams/" + teamid, team, Team.class);

        return ResponseEntity.ok().build();
    }

}
