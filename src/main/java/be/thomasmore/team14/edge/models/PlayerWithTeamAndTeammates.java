package be.thomasmore.team14.edge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerWithTeamAndTeammates {
    private int id;
    private String gamerTag;
    private String realName;
    private int age;
    private String mouse;
    private String mousepad;
    private String keyboard;
    private String headset;
    private String picture;
    private String teamName;
    private String teamLogo;
    private List<Player> teammembers;

    public PlayerWithTeamAndTeammates() {
    }

    public PlayerWithTeamAndTeammates(Player player, String teamName, String teamLogo, List<Player> teammembers) {
        this.id = player.getId();
        this.gamerTag = player.getGamerTag();
        this.realName = player.getRealName();
        this.age = player.getAge();
        this.mouse = player.getMouse();
        this.mousepad = player.getMousepad();
        this.keyboard = player.getKeyboard();
        this.headset = player.getHeadset();
        this.picture = player.getPicture();
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.teammembers = teammembers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
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
}
