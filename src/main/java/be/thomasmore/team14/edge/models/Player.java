package be.thomasmore.team14.edge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "All details about a player")
public class Player {
    @ApiModelProperty(notes = "The database generated player ID")
    private int id;
    @ApiModelProperty(notes = "The gamertag of the player")
    private String gamerTag;
    @ApiModelProperty(notes = "The real name of the player")
    private String realName;
    @ApiModelProperty(notes = "The age of the player")
    private int age;
    @ApiModelProperty(notes = "The preferred mouse of the player")
    private String mouse;
    @ApiModelProperty(notes = "The preferred mousepad of the player")
    private String mousepad;
    @ApiModelProperty(notes = "The preferred keyboard of the player")
    private String keyboard;
    @ApiModelProperty(notes = "The preferred headset of the player")
    private String headset;
    @ApiModelProperty(notes = "A link to a picture of the player")
    private String picture;
    @ApiModelProperty(notes = "The id of the team of the player")
    private String teamId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGamerTag() {
        return gamerTag;
    }

    public void setGamerTag(String gamerTag) {
        this.gamerTag = gamerTag;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public String getMousepad() {
        return mousepad;
    }

    public void setMousepad(String mousepad) {
        this.mousepad = mousepad;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public String getHeadset() {
        return headset;
    }

    public void setHeadset(String headset) {
        this.headset = headset;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
