package be.thomasmore.team14.edge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "All details about a Tournament")
public class Tournament {
    @ApiModelProperty(notes = "The database generated team ID")
    private String id;
    @ApiModelProperty(notes = "The name of the tournament")
    private String name;
    @ApiModelProperty(notes = "The id of the game that the tournament is hosting")
    private String gameId;
    @ApiModelProperty(notes = "A list of IDs of the teams that are paticipating")
    private String[] teams;
    @ApiModelProperty(notes = "A link to the image of the tournament")
    private String imageUrl;
    @ApiModelProperty(notes = "A description of the tournament")
    private String description;

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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String[] getTeams() {
        return teams;
    }

    public void setTeams(String[] teams) {
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
