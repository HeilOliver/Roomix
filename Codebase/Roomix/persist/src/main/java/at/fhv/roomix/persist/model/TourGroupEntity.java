package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TourGroup", schema = "Roomix", catalog = "")
public class TourGroupEntity {
    private int tourGroupId;
    private String tourGroupName;
    private int tourGroupLeader;
    private PersonEntity personByTourGroupLeader;
    private Collection<TourGroupMemberEntity> tourGroupMembersByTourGroupId;

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
    @Column(name = "TourGroupLeader", updatable = false, insertable = false)
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
        TourGroupEntity that = (TourGroupEntity) o;
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

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "tourGroupByTourGroupId")
    public Collection<TourGroupMemberEntity> getTourGroupMembersByTourGroupId() {
        return tourGroupMembersByTourGroupId;
    }

    public void setTourGroupMembersByTourGroupId(Collection<TourGroupMemberEntity> tourGroupMembersByTourGroupId) {
        this.tourGroupMembersByTourGroupId = tourGroupMembersByTourGroupId;
    }
}
