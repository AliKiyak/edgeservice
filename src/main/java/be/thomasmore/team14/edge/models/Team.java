package be.thomasmore.team14.edge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "All details about a Team")
public class Team {
    @ApiModelProperty(notes = "The database generated team ID")
    private String id;
    @ApiModelProperty(notes = "The name of the team")
    private String name;
    @ApiModelProperty(notes = "The name of the owner of the team")
    private String owner;
    @ApiModelProperty(notes = "A small description of the team")
    private String description;
    @ApiModelProperty(notes = "The id of the game that the team mainly plays")
    private String gameId;
    @ApiModelProperty(notes = "A link to an image of the team")
    private String imageUrl;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
