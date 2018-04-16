package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TourGroupMember", schema = "Roomix", catalog = "")
@IdClass(TourGroupMemberEntityPK.class)
public class TourGroupMemberEntity {
    private int tourGroupId;
    private int tourGroupMember;
    private TourGroupEntity tourGroupByTourGroupId;
    private PersonEntity personByTourGroupMember;

    @Id
    @Column(name = "TourGroupID", insertable = false, updatable = false)
    public int getTourGroupId() {
        return tourGroupId;
    }

    public void setTourGroupId(int tourGroupId) {
        this.tourGroupId = tourGroupId;
    }

    @Id
    @Column(name = "TourGroupMember", insertable = false, updatable = false)
    public int getTourGroupMember() {
        return tourGroupMember;
    }

    public void setTourGroupMember(int tourGroupMember) {
        this.tourGroupMember = tourGroupMember;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourGroupMemberEntity that = (TourGroupMemberEntity) o;
        return tourGroupId == that.tourGroupId &&
                tourGroupMember == that.tourGroupMember;
    }

    @Override
    public int hashCode() {

        return Objects.hash(tourGroupId, tourGroupMember);
    }

    @ManyToOne
    @JoinColumn(name = "TourGroupID", referencedColumnName = "TourGroupID", nullable = false, insertable = false, updatable = false)
    public TourGroupEntity getTourGroupByTourGroupId() {
        return tourGroupByTourGroupId;
    }

    public void setTourGroupByTourGroupId(TourGroupEntity tourGroupByTourGroupId) {
        this.tourGroupByTourGroupId = tourGroupByTourGroupId;
    }

    @ManyToOne
    @JoinColumn(name = "TourGroupMember", referencedColumnName = "PersonID", nullable = false, insertable = false, updatable = false)
    public PersonEntity getPersonByTourGroupMember() {
        return personByTourGroupMember;
    }

    public void setPersonByTourGroupMember(PersonEntity personByTourGroupMember) {
        this.personByTourGroupMember = personByTourGroupMember;
    }
}
