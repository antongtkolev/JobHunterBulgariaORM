package DbJobHunter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Advertisements", schema = "dbo", catalog = "JobHunterBulgaria")
public class AdvertisementsEntity extends  BaseClass{

    private String description;
    private boolean active;
    private EmployerAccountEntity employer;
    private ProfessionCategoryEntity professionCategory;
    private Set<CandidateEntity> candidate = new HashSet<>();


    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST })
    @JoinTable(
            name = "Candidate_record",
            joinColumns = { @JoinColumn(name = "Advertisement_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Candidate_ID") }
    )
    public Set<CandidateEntity> getCandidate() {
        return candidate;
    }

    public void setCandidate(Set<CandidateEntity> candidate) {
        this.candidate = candidate;
    }



    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "Employer_acc_ID")
    public EmployerAccountEntity getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerAccountEntity employer) {
        this.employer = employer;
    }

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "Prof_cat_ID")
    public ProfessionCategoryEntity getProfessionCategory() {
        return professionCategory;
    }

    public void setProfessionCategory(ProfessionCategoryEntity professionCategory) {
        this.professionCategory = professionCategory;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvertisementsEntity that = (AdvertisementsEntity) o;
        return id == that.id &&
                active == that.active &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, active);
    }
}
