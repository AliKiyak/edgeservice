package be.thomasmore.team14.edge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamWithGame {
    private String id;
    private String name;
    private String owner;
    private String description;
    private String game;
    private String imageUrl;

    public TeamWithGame() {

    }
    public TeamWithGame(Team team, String game) {
        this.name = team.getName();
        this.owner = team.getOwner();
        this.description = team.getDescription();
        this.game = game;
        this.imageUrl = team.getImageUrl();
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

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
