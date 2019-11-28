package be.thomasmore.team14.edge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "All details about a game")
public class Game {
    @ApiModelProperty(notes = "The database generated game ID")
    private String id;
    @ApiModelProperty(notes = "The title of the game")
    private String title;
    @ApiModelProperty(notes = "The name of the creator of the game")
    private String creator;
    @ApiModelProperty(notes = "A link to the image of the team logo")
    private String imageUrl;
    @ApiModelProperty(notes = "A small description about the game")
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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
