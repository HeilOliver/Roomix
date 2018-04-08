package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "tourgroup", schema = "roomix", catalog = "")
public class TourgroupEntity {
    private int tourGroupId;
    private String tourGroupName;
    private int tourGroupLeader;
    private PersonEntity personByTourGroupLeader;
    private Collection<TourgroupmemberEntity> tourgroupmembersByTourGroupId;

    @Id
    @Column(name = "TourGroupID")
    public int getTourGroupId() {
        return tourGroupId;
    }

    public void setTourGroupId(int tourGroupId) {
        this.tourGroupId = tourGroupId;
    }

    @Basic
    @Column(name = "TourGroupName")
    public String getTourGroupName() {
        return tourGroupName;
    }

    public void setTourGroupName(String tourGroupName) {
        this.tourGroupName = tourGroupName;
    }

    @Basic
    @Column(name = "TourGroupLeader", insertable=false, updatable=false)
    public int getTourGroupLeader() {
        return tourGroupLeader;
    }

    public void setTourGroupLeader(int tourGroupLeader) {
        this.tourGroupLeader = tourGroupLeader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourgroupEntity that = (TourgroupEntity) o;
        return tourGroupId == that.tourGroupId &&
                tourGroupLeader == that.tourGroupLeader &&
                Objects.equals(tourGroupName, that.tourGroupName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tourGroupId, tourGroupName, tourGroupLeader);
    }

    @ManyToOne
    @JoinColumn(name = "TourGroupLeader", referencedColumnName = "PersonID", nullable = false)
    public PersonEntity getPersonByTourGroupLeader() {
        return personByTourGroupLeader;
    }

    public void setPersonByTourGroupLeader(PersonEntity personByTourGroupLeader) {
        this.personByTourGroupLeader = personByTourGroupLeader;
    }

    @OneToMany(mappedBy = "tourgroupByTourGroupId")
    public Collection<TourgroupmemberEntity> getTourgroupmembersByTourGroupId() {
        return tourgroupmembersByTourGroupId;
    }

    public void setTourgroupmembersByTourGroupId(Collection<TourgroupmemberEntity> tourgroupmembersByTourGroupId) {
        this.tourgroupmembersByTourGroupId = tourgroupmembersByTourGroupId;
    }
}
