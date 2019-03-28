package one.vladimir.wts.DBModule.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "group_")
public class Group {
    @Id
    @GeneratedValue
    private Integer GroupId;

    private Integer Koef;

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
