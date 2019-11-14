package be.thomasmore.team14.edge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TournamentJoined {
    private String id;
    private String name;
    private Game game;
    private List<TeamWithGame> teams;
    private String imageUrl;
    private String description;

    public TournamentJoined() {}
    public TournamentJoined(Tournament tournament, List<TeamWithGame> teams, Game game) {
        this.id = tournament.getId();
        this.name = tournament.getName();
        this.imageUrl = tournament.getImageUrl();
        this.description = tournament.getDescription();
        this.game = game;
        this.teams = teams;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<TeamWithGame> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamWithGame> teams) {
        this.teams = teams;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
