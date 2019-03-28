package one.vladimir.wts.DBModule.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Point {
    @Id
    @GeneratedValue
    private Integer pointId;

    @ManyToOne
    @JoinColumn(name = "CreatorId")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "GroupId")
    private Group group;

    @OneToOne(mappedBy = "point", cascade = CascadeType.ALL)
    private Dump dump;

    @OneToOne(mappedBy = "point", cascade = CascadeType.ALL)
    private Base base;

    @OneToMany(mappedBy = "point", cascade = CascadeType.ALL)
    private Collection<RoutePoint> routePoints;


    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
