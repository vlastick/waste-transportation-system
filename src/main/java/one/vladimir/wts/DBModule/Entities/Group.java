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
    private Long GroupId;

    private Integer Koef;

    public Long getGroupId() {
        return GroupId;
    }

    public void setGroupId(Long groupId) {
        GroupId = groupId;
    }

    public Integer getKoef() {
        return Koef;
    }

    public void setKoef(Integer koef) {
        Koef = koef;
    }
}
