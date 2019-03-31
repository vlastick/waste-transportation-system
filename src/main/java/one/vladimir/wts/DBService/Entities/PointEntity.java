package one.vladimir.wts.DBService.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class PointEntity {
    @Id
    @GeneratedValue
    private Integer pointId;

    @ManyToOne
    @JoinColumn(name = "CreatorId")
    private UserEntity creator;

    @ManyToOne
    @JoinColumn(name = "GroupId")
    private GroupEntity group;

    @OneToOne(mappedBy = "point", cascade = CascadeType.ALL)
    private DumpEntity dump;

    @OneToOne(mappedBy = "point", cascade = CascadeType.ALL)
    private BaseEntity base;

    @OneToMany(mappedBy = "point", cascade = CascadeType.ALL)
    private Collection<RoutePointEntity> routePoints;


    //    getters and setters
    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
}
