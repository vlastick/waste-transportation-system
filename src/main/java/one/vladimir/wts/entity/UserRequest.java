package one.vladimir.wts.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserRequest {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;

    private Integer userId;

    private Integer pointId;

    private String name;

    private String description;

    private Double weight;

    private Double volume;

/*                          Kerouac with love <3

    public UserRequest(Integer pointID, Integer userID,
                       String name, String description,
                       Double weight, Double volume){

        if((pointID < 0) || (userID < 0)
                || (name.isEmpty()) || (description.isEmpty())
                || (weight < 0) || volume < 0 ){
            this.pointID = 0;
            this.userID = 0;
            this.name = "Error";
            this.description = "Error";
            this.weight = 0.0;
            this.volume = 0.0;
            System.out.println("Error. Incorrect input data for user request");
        }
        else{
            this.pointID = pointID;
            this.userID = userID;
            this.name = name;
            this.description = description;
            this.weight = weight;
            this.volume = volume;
        }


    }*/

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
