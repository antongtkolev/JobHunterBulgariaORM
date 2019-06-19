package DbJobHunter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Employer_account", schema = "dbo", catalog = "JobHunterBulgaria")
public class EmployerAccountEntity extends BaseClass {

    private String email;
    private Integer advertisementCounter;
    private Set<AdvertisementsEntity> advertisements = new HashSet<>();



    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST })
    @JoinColumn(name = "Employer_acc_ID")
    public Set<AdvertisementsEntity> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(Set<AdvertisementsEntity> advertisements) {
        this.advertisements = advertisements;
    }


    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Advertisement_counter")
    public Integer getAdvertisementCounter() {
        return advertisementCounter;
    }

    public void setAdvertisementCounter(Integer advertisementCounter) {
        this.advertisementCounter = advertisementCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployerAccountEntity that = (EmployerAccountEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(advertisementCounter, that.advertisementCounter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, advertisementCounter);
    }
}
