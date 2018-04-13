package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TourGroupMember", schema = "roomix", catalog = "")
public class TourgroupmemberEntity {
    private int tourGroupMemberId;
    private Integer tourGroupId;
    private int tourGroupMember;
    private TourgroupEntity tourgroupByTourGroupId;
    private PersonEntity personByTourGroupMember;

    @Id
    @Column(name = "TourGroupMemberID")
    public int getTourGroupMemberId() {
        return tourGroupMemberId;
    }

    public void setTourGroupMemberId(int tourGroupMemberId) {
        this.tourGroupMemberId = tourGroupMemberId;
    }

    @Basic
    @Column(name = "TourGroupID", insertable = false, updatable = false)
    public Integer getTourGroupId() {
        return tourGroupId;
    }

    public void setTourGroupId(Integer tourGroupId) {
        this.tourGroupId = tourGroupId;
    }

    @Basic
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
        TourgroupmemberEntity that = (TourgroupmemberEntity) o;
        return tourGroupMemberId == that.tourGroupMemberId &&
                tourGroupMember == that.tourGroupMember &&
                Objects.equals(tourGroupId, that.tourGroupId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tourGroupMemberId, tourGroupId, tourGroupMember);
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToOne
    @JoinColumn(name = "TourGroupID", referencedColumnName = "TourGroupID")
    public TourgroupEntity getTourgroupByTourGroupId() {
        return tourgroupByTourGroupId;
    }

    public void setTourgroupByTourGroupId(TourgroupEntity tourgroupByTourGroupId) {
        this.tourgroupByTourGroupId = tourgroupByTourGroupId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToOne
    @JoinColumn(name = "TourGroupMember", referencedColumnName = "PersonID", nullable = false)
    public PersonEntity getPersonByTourGroupMember() {
        return personByTourGroupMember;
    }

    public void setPersonByTourGroupMember(PersonEntity personByTourGroupMember) {
        this.personByTourGroupMember = personByTourGroupMember;
    }
}
