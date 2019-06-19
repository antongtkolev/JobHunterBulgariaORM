package DbJobHunter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Profession_Category", schema = "dbo", catalog = "JobHunterBulgaria")
public class ProfessionCategoryEntity extends  BaseClass{

    private Set<AdvertisementsEntity> advertisements = new HashSet<>();



    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST })
    @JoinColumn(name = "Prof_cat_ID")
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
        ProfessionCategoryEntity that = (ProfessionCategoryEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
