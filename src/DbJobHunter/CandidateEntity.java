package DbJobHunter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Candidate", schema = "dbo", catalog = "JobHunterBulgaria")
public class CandidateEntity extends BaseClass {

    private Set<AdvertisementsEntity> advertisements = new HashSet<>();

    @ManyToMany (cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST })
    public Set<AdvertisementsEntity> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(Set<AdvertisementsEntity> advertisements) {
        this.advertisements = advertisements;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateEntity that = (CandidateEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
