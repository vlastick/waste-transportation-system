package one.vladimir.wts.DBService.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "group_")
public class Group {
    @Id
    @GeneratedValue
    private Integer GroupId;

    private Integer Koef;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Collection<Point> points;


    //    getters and setters
    public Integer getGroupId() {
        return GroupId;
    }

    public void setGroupId(Integer groupId) {
        GroupId = groupId;
    }

    public Integer getKoef() {
        return Koef;
    }

    public void setKoef(Integer koef) {
        Koef = koef;
    }
}
