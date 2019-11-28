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
@RequestMapping("/tournament")
@Api(value="Tournament controller", description="Actions that involve the Tournament entity")
public class TournamentController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation(value = "Returns a list of all the tournaments", response = List.class)
    @GetMapping("/tournaments")
    public List<TournamentJoined> getTournaments() {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
          "http://tournament-service/tournaments", GenericResponseWrapper.class
        );

        List<Tournament> tournaments = objectMapper.convertValue(wrapper.get_embedded().get("tournaments"), new TypeReference<List<Tournament>>() {});

        List<TournamentJoined> returnList = new ArrayList<>();

        for (Tournament tournament : tournaments ) {
            Game game = restTemplate.getForObject("http://game-service/games/" + tournament.getGameId(), Game.class);
            List<TeamWithGame> teams = new ArrayList<>();

            for(String teamId: tournament.getTeams()) {
                Team team = restTemplate.getForObject("http://team-service/teams/" + teamId, Team.class);

                teams.add(new TeamWithGame(team, game.getTitle()));
            }

            returnList.add(new TournamentJoined(tournament, teams, game));
        }

        return returnList;
    }

    @ApiOperation(value = "Returns a list of tournaments that contain a certain string in their name", response = List.class)
    @GetMapping("/tournaments/filter/{name}")
    public List<TournamentJoined> getTournamentsFiltered(@PathVariable("name") String name) {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://tournament-service/tournaments/search/findTournamentByNameContainingIgnoreCase?name=" + name, GenericResponseWrapper.class
        );

        List<Tournament> tournaments = objectMapper.convertValue(wrapper.get_embedded().get("tournaments"), new TypeReference<List<Tournament>>() {});

        List<TournamentJoined> returnList = new ArrayList<>();

        for (Tournament tournament : tournaments ) {
            Game game = restTemplate.getForObject("http://game-service/games/" + tournament.getGameId(), Game.class);
            List<TeamWithGame> teams = new ArrayList<>();

            for(String teamId: tournament.getTeams()) {
                Team team = restTemplate.getForObject("http://team-service/teams/" + teamId, Team.class);

                teams.add(new TeamWithGame(team, game.getTitle()));
            }

            returnList.add(new TournamentJoined(tournament, teams, game));
        }

        return returnList;
    }

    @ApiOperation(value = "Returns a tournaments of a certain game using its id", response = List.class)
    @GetMapping("/tournaments/game/{gameid}")
    public List<TournamentJoined> getTournamentsFromCertainGame(@PathVariable("gameid") String gameid) {
        GenericResponseWrapper wrapper = restTemplate.getForObject(
                "http://tournament-service/tournaments/search/findTournamentByGameId?gameid=" + gameid, GenericResponseWrapper.class
        );

        List<Tournament> tournaments = objectMapper.convertValue(wrapper.get_embedded().get("tournaments"), new TypeReference<List<Tournament>>() {});

        List<TournamentJoined> returnList = new ArrayList<>();

        for (Tournament tournament : tournaments ) {
            Game game = restTemplate.getForObject("http://game-service/games/" + tournament.getGameId(), Game.class);
            List<TeamWithGame> teams = new ArrayList<>();

            for(String teamId: tournament.getTeams()) {
                Team team = restTemplate.getForObject("http://team-service/teams/" + teamId, Team.class);

                teams.add(new TeamWithGame(team, game.getTitle()));
            }

            returnList.add(new TournamentJoined(tournament, teams, game));
        }

        return returnList;
    }

    @ApiOperation(value = "Returns a single tournament using its id", response = List.class)
    @GetMapping("/detail/{tournamentid}")
    public TournamentJoined getDetailTournament(@PathVariable("tournamentid") String tournamentid) {
        Tournament tournament = restTemplate.getForObject(
                "http://tournament-service/tournaments/search/findTournamentById?tournamentid="  + tournamentid, Tournament.class
        );
            Game game = restTemplate.getForObject("http://game-service/games/" + tournament.getGameId(), Game.class);
            List<TeamWithGame> teams = new ArrayList<>();

            for(String teamId: tournament.getTeams()) {
                Team team = restTemplate.getForObject("http://team-service/teams/" + teamId, Team.class);

                teams.add(new TeamWithGame(team, game.getTitle()));
            }

            return new TournamentJoined(tournament, teams, game);
    }

    @ApiOperation(value = "Adds a tournament to the database", response = List.class)
    @PostMapping("/addtournament")
    public ResponseEntity<String> postGame(@RequestBody Tournament tournament) {
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
        ResponseEntity<Tournament> result = restTemplate.postForEntity(
                "http://tournament-service/tournaments/", tournament, Tournament.class
        );

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Deletes a tournament from the database", response = List.class)
    @DeleteMapping("/deletetournament/{tournamentid}")
    public ResponseEntity deleteTournament(@PathVariable("tournamentid") String tournamentId) {
        restTemplate.delete("http://tournament-service/tournaments/" + tournamentId);

        return ResponseEntity.ok().build();
    }
}
